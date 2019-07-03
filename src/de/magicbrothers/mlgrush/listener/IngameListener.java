package de.magicbrothers.mlgrush.listener;

import de.magicbrothers.mlgrush.games.Game;
import de.magicbrothers.mlgrush.main.Main;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

            p.setFallDistance(0);
            p.setHealth(20);
            p.setSaturation(20);

            Configuration cfg = Main.getPlugin().getConfig();
            Game game = games.get(p.getName());
            int arena = game.getArena();
            int height = cfg.getInt("games." + arena + ".height");

            if(p.getLocation().getY() <= height) {
                game.tpToSpawn(p.getName());
            }

        }

    }

}
