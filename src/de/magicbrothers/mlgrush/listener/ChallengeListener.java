package de.magicbrothers.mlgrush.listener;

import de.magicbrothers.mlgrush.games.Game;
import de.magicbrothers.mlgrush.main.Main;
import de.magicbrothers.mlgrush.stuff.Message;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class ChallengeListener implements Listener {

    @EventHandler
    public void onChallenge(EntityDamageByEntityEvent e) {

        if(!(e.getDamager() instanceof Player && e.getEntity() instanceof Player))  { return; }

        Player p = (Player) e.getDamager();
        Player pother = (Player) e.getEntity();

        if(!p.getItemInHand().getType().equals(Material.DIAMOND_SWORD)) { return; }

        ArrayList<String> players = Main.getPlugin().getMlgLobbyPlayers();

        if(players.contains(p.getName()) && players.contains(pother.getName())) { // sind beide in MLGRush?

            e.setCancelled(true);

            HashMap<String, String> challengedPlayers = Main.getPlugin().getChallengePlayers();

            if(challengedPlayers.containsKey(pother.getName()) && challengedPlayers.get(pother.getName()).equals(p.getName())) { // wurde p schon von pother herausgefordert?
                p.sendMessage(Main.PREFIX + Message.getMessage("challenge_accept", "%player%", pother.getDisplayName()));
                pother.sendMessage(Main.PREFIX + Message.getMessage("challenge_accepted", "%player%", p.getDisplayName()));
                int game = Game.getNewArena();

                if(game != -1) {
                    String[] ps = {p.getName(), pother.getName()};
                    Game arena = new Game(ps, game);
                    arena.play();
                }

            } else {
                Main.getPlugin().challenge(p.getName(), pother.getName());
                p.sendMessage(Main.PREFIX + Message.getMessage("challenge", "%player%", pother.getDisplayName()));
                pother.sendMessage(Main.PREFIX + Message.getMessage("challenged", "%player%", p.getDisplayName()));
            }
        }

    }

}
