package de.magicbrothers.mlgrush.listener;

import de.magicbrothers.mlgrush.games.Game;
import de.magicbrothers.mlgrush.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

        Player p = e.getPlayer();
        String name = p.getName();
        Main.getPlugin().removeLobbyPlayer(name);
        Main.getPlugin().removeChallengePlayer(name);
        if(Main.getPlugin().getMlgRushPlayers().contains(name)) {
            Game game = Main.getPlugin().getPlayerGames().get(name);
            game.quitGame(name);
            e.setQuitMessage("");
        }

    }

}
