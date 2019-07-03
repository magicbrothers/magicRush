package de.magicbrothers.mlgrush.stuff;

import de.magicbrothers.mlgrush.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class Locations {

    public static Location getLocation(String path, String name) {
        Configuration cfg = Main.getPlugin().getConfig();

        if(!cfg.contains(path + "." + name + ".world")) {
            Bukkit.broadcastMessage(Main.PREFIX + "§cDie Location für \"" + name + "\" wurde noch nicht gesetzt!");
            return ((Player) Bukkit.getOnlinePlayers().toArray()[0]).getLocation();
        }

        String world = cfg.getString(path + "." + name + ".world");
        double x = cfg.getDouble(path + "." + name + ".x");
        double y = cfg.getDouble(path + "." + name + ".y");
        double z = cfg.getDouble(path + "." + name + ".z");
        float yaw = (float) cfg.getDouble(path + "." + name + ".yaw");
        float  pitch = (float) cfg.getDouble(path + "." + name + ".pitch");

        Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);

        return loc;
    }

    public static void setLocation(String path, String name, String world, double x, double y, double z, float yaw, float pitch) {

        Configuration cfg = Main.getPlugin().getConfig();
        path += "." + name + ".";

        cfg.set(path + "world", world);
        cfg.set(path + "x", x);
        cfg.set(path + "y", y);
        cfg.set(path + "z", z);
        cfg.set(path + "yaw", yaw);
        cfg.set(path + "pitch", pitch);

        Main.getPlugin().saveConfig();

    }

}
