package de.magicbrothers.mlgrush.main;

import de.magicbrothers.mlgrush.commands.magicrushCommand;
import de.magicbrothers.mlgrush.games.Game;
import de.magicbrothers.mlgrush.listener.ChallengeListener;
import de.magicbrothers.mlgrush.listener.IngameListener;
import de.magicbrothers.mlgrush.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends JavaPlugin {

    private static Main plugin;

    public static final String PREFIX = "§3MLGRush§8 | ",
                                NO_PERMISSION = PREFIX + "§cDazu hast du keine Rechte! Falls das ein Fehler ist, melde dich im Support oder bei einem Owner.";

    private ArrayList<String> mlgRushPlayers;
    private ArrayList<String> mlgLobbyPlayers;
    private HashMap<String, String> challengePlayers;
    private HashMap<String, Game> playerGames;

    public void onEnable() {

        plugin = this;
        loadConfig();
        System.out.println("--- magicRush wurde erfolgreich aktiviert! ---");

        mlgRushPlayers = new ArrayList<>();
        mlgLobbyPlayers = new ArrayList<>();
        challengePlayers = new HashMap<>();
        playerGames = new HashMap<>();

        getCommand("magicrush").setExecutor(new magicrushCommand());

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new JoinListener(), plugin);
        pm.registerEvents(new ChallengeListener(), plugin);
        pm.registerEvents(new IngameListener(), plugin);

    }

    private void loadConfig() {
        boolean copyDefaults = true;
        if(getConfig().contains("copyDefaults")) {
            copyDefaults = getConfig().getBoolean("copyDefaults");
        }

        if(copyDefaults) {
            getConfig().options().copyDefaults(true);
            getConfig().options().copyDefaults();
            saveConfig();
        }
    }

    public static Main getPlugin() {
        return plugin;
    }

    public ArrayList<String> getMlgLobbyPlayers() {
        return mlgLobbyPlayers;
    }

    public ArrayList<String> getMlgRushPlayers() {
        return mlgRushPlayers;
    }

    public HashMap<String, String> getChallengePlayers() {
        return challengePlayers;
    }

    public HashMap<String, Game> getPlayerGames() {
        return playerGames;
    }

    public void addLobbyPlayer(String p) {
        mlgLobbyPlayers.add(p);
    }

    public void addRushPlayer(String p, Game game) {
        mlgRushPlayers.add(p);
        playerGames.put(p, game);
    }

    public void challenge(String challenger, String challenged) {
        challengePlayers.put(challenger, challenged);
    }

    public void removeLobbyPlayer(String p) {
        mlgLobbyPlayers.remove(p);
    }

    public void removeChallengePlayer(String p) {
        challengePlayers.remove(p);
    }

    public void removeRushPlayer(String p) {
        mlgRushPlayers.remove(p);
        playerGames.remove(p);
    }

}
