package de.magicbrothers.mlgrush.listener;

import de.magicbrothers.mlgrush.main.Main;
import de.magicbrothers.mlgrush.stuff.Locations;
import de.magicbrothers.mlgrush.stuff.Message;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

    @EventHandler
    public void onSignClick(PlayerInteractEvent e) {

        Block block = e.getClickedBlock();
        Player p = e.getPlayer();

        if(e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_AIR)) return;

        if(block.getType().equals(Material.WALL_SIGN) && e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Sign sign = (Sign) block.getState();
            if(sign.getLine(0).contains("MLGRush")) {
                Main.getPlugin().addLobbyPlayer(p.getName());
                p.teleport(Locations.getLocation("locations", "lobby"));
                p.sendMessage(Main.PREFIX + Message.getMessage("tp_to_mlgrush_lobby"));
                p.sendMessage(Main.PREFIX + "§1Plugin by magicbrothers");
                p.getInventory().clear();
                ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
                p.getInventory().setItem(0, sword);
            }

        }

    }

}
