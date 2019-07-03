package de.magicbrothers.mlgrush.commands;

import de.magicbrothers.mlgrush.main.Main;
import de.magicbrothers.mlgrush.stuff.Locations;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.List;

public class magicrushCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(p.hasPermission("magicrush.setup")) {
                Configuration cfg = Main.getPlugin().getConfig();
                if(args.length > 0) {
                    if(args[0].equals("setloc")) {

                        Location loc = p.getLocation();

                        if(args.length == 2) {
                            Locations.setLocation("locations", args[1], loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                            p.sendMessage(Main.PREFIX + "§aDu hast erfolgreich die Location für \"" + args[1] + "\" gesetzt.");
                        } else if(args.length == 3) {
                            Locations.setLocation("games", args[1] + "." + args[2], loc.getWorld().getName(), loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
                            p.sendMessage(Main.PREFIX + "§aDu hast erfolgreich die Location für \"" + args[2] + "\" im Game " + args[1] + " gesetzt.");
                        }

                    } else if(args[0].equals("addgame") && args.length == 2) {

                        int games = cfg.getInt("games.games");
                        cfg.set("games." + (games+1) + ".name", args[1]);
                        cfg.set("games.games", games + 1);
                        Main.getPlugin().saveConfig();
                        p.sendMessage(Main.PREFIX + "§aDu hast erfolgreich das Game §6" + args[1] + "§a mit der ID §6" + (games+1) + "§a erstellt!");

                    } else if(args[0].equals("reload")) {

                        Main.getPlugin().reloadConfig();
                        p.sendMessage(Main.PREFIX + "§aDie Config wurde neu geladen.");

                    } else if(args[0].equals("activate") && args.length == 2) {

                        List<Integer> activated = cfg.getIntegerList("games.activated");
                        activated.add(Integer.parseInt(args[1]));

                        cfg.set("games.activated", activated);
                        Main.getPlugin().saveConfig();

                    }
                } else {
                    p.sendMessage(Main.PREFIX + "§cBitte gib weitere Argumente an.");
                    return false;
                }
            } else p.sendMessage(Main.NO_PERMISSION);

        }

        return true;
    }
}
