package de.magicbrothers.mlgrush.games;

import de.magicbrothers.mlgrush.main.Main;
import de.magicbrothers.mlgrush.stuff.Locations;
import de.magicbrothers.mlgrush.stuff.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private static ArrayList<Integer> arenas = new ArrayList<>();
    private static ArrayList<Game> games = new ArrayList<>();
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

    public void tpToSpawn(String p) {

        int pnumber = 0;
        if(players[1].equals(p)) pnumber = 1;

        Location loc = Locations.getLocation("games." + arena, "spawn" + (pnumber + 1));
        Player player = Bukkit.getPlayer(p);

        player.teleport(loc);
        fillInventory(p);

    }

    public void fillInventory(String p) {

        Player player = Bukkit.getPlayer(p);

        ItemStack stick = new ItemStack(Material.STICK);
        ItemStack pickaxe = new ItemStack(Material.STONE_PICKAXE);
        ItemStack blocks = new ItemStack(Material.SANDSTONE, 32);

        ItemMeta stickMeta = stick.getItemMeta();
        stickMeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        stick.setItemMeta(stickMeta);

        player.getInventory().setItem(0, stick);
        player.getInventory().setItem(1, pickaxe);
        player.getInventory().setItem(8, blocks);

    }

    public String[] getPlayers() {
        return players;
    }

    public int getArena() {
        return arena;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }
}
