package de.magicbrothers.mlgrush.games;

import de.magicbrothers.mlgrush.main.Main;
import de.magicbrothers.mlgrush.stuff.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static ArrayList<Integer> arenas = new ArrayList<>();
    public static ArrayList<Game> games = new ArrayList<>();
    private String[] players;
    private int arena;

    private static Configuration cfg = Main.getPlugin().getConfig();

    public Game(String[] players, int arena) {

        this.players = players;
        this.arena = arena;

    }

    public void play() {

        Player p1 = Bukkit.getPlayer(players[0]);
        Player p2 = Bukkit.getPlayer(players[1]);

        Main.getPlugin().removeChallengePlayer(p1.getName());
        Main.getPlugin().removeChallengePlayer(p2.getName());
        Main.getPlugin().removeLobbyPlayer(p1.getName());
        Main.getPlugin().removeLobbyPlayer(p2.getName());
        Main.getPlugin().addRushPlayer(p1.getName(), this);
        Main.getPlugin().addRushPlayer(p2.getName(), this);

        tpToSpawn(p1.getName());
        tpToSpawn(p2.getName());

        p1.sendMessage(Main.PREFIX + Message.getMessage("game_starts"));
        p2.sendMessage(Main.PREFIX + Message.getMessage("game_starts"));

    }

    public static int getNewArena() {

        List<Integer> active = cfg.getIntegerList("games.activated");
        Bukkit.broadcastMessage(active.toString());
        int arena = -1;

        for(int i : active) {
            if(!arenas.contains(i)) {
                arena = i;
                arenas.add(i);
                break;
            }
        }

        return arena;

    }

    public void tpToSpawn(String player) {

        int pnumber = 0;
        if(players[1].equals(player)) { pnumber = 1; }

        String world = cfg.getString("games." + arena + pnumber + ".world");
        double x = cfg.getDouble("games." + arena + pnumber + ".x");
        double y = cfg.getDouble("games." + arena + pnumber + ".y");
        double z = cfg.getDouble("games." + arena + pnumber + ".z");
        float yaw = (float) cfg.getDouble("games." + arena + pnumber + ".yaw");
        float  pitch = (float) cfg.getDouble("games." + arena + pnumber + ".pitch");

        Location loc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        Player p = Bukkit.getPlayer(players[0]);

        p.teleport(loc);

    }

    public String[] getPlayers() {
        return players;
    }

    public int getArena() {
        return arena;
    }
}
