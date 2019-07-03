package de.magicbrothers.mlgrush.stuff;

import de.magicbrothers.mlgrush.main.Main;

public class Message {

    public static String getMessage(String path, String search, String replace) {
        return Main.getPlugin().getConfig().getString("messages." + path).replace(search, replace);
    }

    public static String getMessage(String path) {
        return Main.getPlugin().getConfig().getString("messages." + path);
    }

}
