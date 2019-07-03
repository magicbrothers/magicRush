package de.magicbrothers.mlgrush.listener;

import de.magicbrothers.mlgrush.games.Game;
import de.magicbrothers.mlgrush.main.Main;
import de.magicbrothers.mlgrush.stuff.Locations;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class IngameListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {

        ArrayList<String> players = Main.getPlugin().getMlgRushPlayers();
        HashMap<String, Game> games = Main.getPlugin().getPlayerGames();

        Player p = e.getPlayer();
        if(players.contains(p.getName())) {

            p.setHealth(20);
            p.setFoodLevel(20);

            Configuration cfg = Main.getPlugin().getConfig();
            Game game = games.get(p.getName());
            int arena = game.getArena();
            int height = cfg.getInt("games." + arena + ".height");

            if(p.getLocation().getY() <= height) {
                game.tpToSpawn(p.getName());
            }

        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        if(!Main.getPlugin().getMlgRushPlayers().contains(e.getPlayer().getName())) return;

        Block block = e.getBlock();
        Location blockloc = block.getLocation();
        Player p = e.getPlayer();
        Game game = Main.getPlugin().getPlayerGames().get(p.getName());

        if(!game.getPlacedBlocks().contains(block.getLocation())) {
            e.setCancelled(true);
        } else game.removePlacedBlock(block.getLocation());

        Location spawnloc1 = Locations.getLocation("games." + game.getArena(), "spawn1");
        Location spawnloc2 = Locations.getLocation("games." + game.getArena(), "spawn2");
        boolean nearer1 = spawnloc1.distance(blockloc) < spawnloc2.distance(blockloc);
        boolean is2 = game.getPlayerNumber(p.getName()) == 2;

        if(block.getType().equals(Material.BED_BLOCK)) {
            if((nearer1 && is2) || (!nearer1 && !is2)) {
                game.bedDestroyed(p.getName());
            }
        }

    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {

        if(!Main.getPlugin().getMlgRushPlayers().contains(e.getPlayer().getName())) return;

        Block block = e.getBlock();
        Location blockloc = block.getLocation();
        Player p = e.getPlayer();
        Game game = Main.getPlugin().getPlayerGames().get(p.getName());

        game.addPlacedBlock(blockloc);

    }

}
