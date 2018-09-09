package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.math.BigInteger;
import java.security.DigestInputStream;
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
    private FileConfiguration itemsFile;

    public ManagedFile(final String filename, final Main ess) {
        file = new File(ess.getDataFolder(), filename);
        itemsFile = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                itemsFile.save(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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