package de.procrafter.flames.flatearth;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FlatEarthLogger {

    public static final Logger log = Logger.getLogger("Minecraft");

    public static void severe(String string, Exception ex) {
        log.log(Level.SEVERE, "[FlatEarth] " + string, ex);
    }

    public static void severe(String string) {
        log.log(Level.SEVERE, "[FlatEarth] " + string);
    }

    static void info(String string) {
        log.log(Level.INFO, "[FlatEarth] " + string);
    }

    static void warning(String string) {
        log.log(Level.WARNING, "[FlatEarth] " + string);
    }
}
