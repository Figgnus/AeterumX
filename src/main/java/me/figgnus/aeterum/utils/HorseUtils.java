package me.figgnus.aeterum.utils;

import me.figgnus.aeterum.AeterumX;
import org.bukkit.Location;
import org.bukkit.entity.AbstractHorse;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class HorseUtils {
    public static void disableCollision(AbstractHorse horse, AeterumX plugin){
        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        //Check if the team exist or create a new one
        Team team = board.getTeam("noCollision");
        if (team == null) {
            team = board.registerNewTeam("noCollision");
            team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        }
        //Add horse to noCollision team
        team.addEntry(horse.getUniqueId().toString());
    }
    public static void enableCollision(AbstractHorse horse, AeterumX plugin){
        ScoreboardManager manager = plugin.getServer().getScoreboardManager();
        Scoreboard board = manager.getMainScoreboard();

        Team team = board.getTeam("noCollision");
        if (team != null) {
            team.removeEntry(horse.getUniqueId().toString());
        }
    }
    public static void showHorse(AbstractHorse horse, AeterumX plugin){
        // Restore visibility and behavior
        horse.removePotionEffect(PotionEffectType.INVISIBILITY);
        horse.setAI(true);
        horse.setCollidable(true);
        horse.setSilent(false);
        horse.setGravity(true);

        // Remove the "unsummoned" tag/metadata
        plugin.setEntityMetadata(horse, "unsummoned", "false");

        // Re-enable collisions
        enableCollision(horse, plugin);
    }
    public static void unsummonHorse(AbstractHorse horse, AeterumX plugin) {
        // Unsummon the horse
        horse.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1, false, false));
        horse.setAI(false);
        horse.setCollidable(false); // This part only affects non-player collisions
        horse.setSilent(true);
        horse.setGravity(false);

        Location horseLocation = horse.getLocation();
        horse.teleport(new Location(horseLocation.getWorld(), horseLocation.getX(), horseLocation.getY() + 500, horseLocation.getZ()));

        // Mark as unsummoned
        plugin.setEntityMetadata(horse, "unsummoned", "true");

        // Disable player collision
        disableCollision(horse, plugin);
    }
}
