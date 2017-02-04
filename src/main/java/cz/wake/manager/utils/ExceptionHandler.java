package cz.wake.manager.utils;

import cz.wake.manager.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.WeakHashMap;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    private final Thread.UncaughtExceptionHandler originalHandler;
    private final WeakHashMap<Main, Boolean> clientMap = new WeakHashMap<Main, Boolean>();
    static final Logger log = LoggerFactory.getLogger(ExceptionHandler.class);

    public static void enable(Main pl) {
        Thread.UncaughtExceptionHandler currentHandler = Thread.getDefaultUncaughtExceptionHandler();

        // Find or create the Bugsnag ExceptionHandler
        ExceptionHandler bugsnagHandler;
        if (currentHandler instanceof ExceptionHandler) {
            bugsnagHandler = (ExceptionHandler) currentHandler;
        } else {
            bugsnagHandler = new ExceptionHandler(currentHandler);
            Thread.setDefaultUncaughtExceptionHandler(bugsnagHandler);
        }

        // Subscribe this bugsnag to uncaught exceptions
        bugsnagHandler.clientMap.put(pl, true);
    }

    public static void disable(Main pl) {
        // Find the Bugsnag ExceptionHandler
        Thread.UncaughtExceptionHandler currentHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (currentHandler instanceof ExceptionHandler) {
            // Unsubscribe this bugsnag from uncaught exceptions
            ExceptionHandler bugsnagHandler = (ExceptionHandler) currentHandler;
            bugsnagHandler.clientMap.remove(pl);

            // Remove the Bugsnag ExceptionHandler if no clients are subscribed
            if (bugsnagHandler.clientMap.size() == 0) {
                Thread.setDefaultUncaughtExceptionHandler(bugsnagHandler.originalHandler);
            }
        }

    }

    ExceptionHandler(Thread.UncaughtExceptionHandler originalHandler) {
        this.originalHandler = originalHandler;
    }

    public void uncaughtException(Thread thread, Throwable throwable) {
        // Notify any subscribed clients of the uncaught exception
        log.error("", throwable);

        // Pass exception on to original exception handler
        if (originalHandler != null) {
            log.error("", throwable);
        } else {
            // Emulate the java exception print style
            System.err.printf("Exception in thread \"%s\" ", thread.getName());
            throwable.printStackTrace(System.err);
            log.error("", throwable);
        }
    }

}
