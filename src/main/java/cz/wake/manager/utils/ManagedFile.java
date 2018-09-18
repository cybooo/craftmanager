package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;

import java.io.*;
import java.math.BigInteger;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;


public class ManagedFile {
    private static final int BUFFERSIZE = 1024 * 8;
    private File file;

    public ManagedFile(final String filename, final Main ess) {
        file = new File(ess.getDataFolder(), filename);

        if (!file.exists()) {
            try {
                copyResourceAscii("/" + filename, file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void copyResourceAscii(final String resourceName, final File file) throws IOException {
        final InputStreamReader reader = new InputStreamReader(ManagedFile.class.getResourceAsStream(resourceName));
        try {
            final MessageDigest digest = getDigest();
            final DigestOutputStream digestStream = new DigestOutputStream(new FileOutputStream(file), digest);
            try {
                final OutputStreamWriter writer = new OutputStreamWriter(digestStream);
                try {
                    final char[] buffer = new char[BUFFERSIZE];
                    do {
                        final int length = reader.read(buffer);
                        if (length >= 0) {
                            writer.write(buffer, 0, length);
                        } else {
                            break;
                        }
                    } while (true);
                    writer.write("\n");
                    writer.flush();
                    final BigInteger hashInt = new BigInteger(1, digest.digest());
                    digestStream.on(false);
                    digestStream.write('#');
                    digestStream.write(hashInt.toString(16).getBytes());
                } finally {
                    writer.close();
                }
            } finally {
                digestStream.close();
            }
        } finally {
            reader.close();
        }
    }

    public static MessageDigest getDigest() throws IOException {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            throw new IOException(ex);
        }
    }

    public List<String> getLines() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(file));
            try {
                final List<String> lines = new ArrayList<String>();
                do {
                    final String line = reader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        lines.add(line);
                    }
                } while (true);
                return lines;
            } finally {
                reader.close();
            }
        } catch (IOException ex) {
            Bukkit.getLogger().log(Level.SEVERE, ex.getMessage(), ex);
            return Collections.emptyList();
        }
    }
}