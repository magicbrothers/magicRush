package de.magicbrothers.mlgrush.stuff;

import de.magicbrothers.mlgrush.main.Main;

public class Message {

    public static String getMessage(String path, String[] search, String[] replace) {
        String message = Main.getPlugin().getConfig().getString("messages." + path);
        for(int i = 0; i < search.length; i++) {
            message = message.replaceAll(search[i], replace[i]);
        }
        return message;
    }

    public static String getMessage(String path, String search, String replace) {
        return Main.getPlugin().getConfig().getString("messages." + path).replaceAll(search, replace);
    }

    public static String getMessage(String path) {
        return Main.getPlugin().getConfig().getString("messages." + path);
    }

}
