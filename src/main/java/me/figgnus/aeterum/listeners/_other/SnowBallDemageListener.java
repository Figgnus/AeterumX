package me.figgnus.aeterum.listeners._other;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class SnowBallDemageListener implements Listener {
    @EventHandler
    public void onSnowballHit(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Snowball) {
            Snowball snowball = (Snowball) event.getDamager();
            if (snowball.getShooter() instanceof Player) {
                Player shooter = (Player) snowball.getShooter(); // Player who threw the snowball

                if (event.getEntity() instanceof Player hitPlayer) {
                    //shooter.getWorld().playSound(shooter.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1.0F, 1.0F);
                    shooter.playSound(shooter.getLocation(), Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1.0F, 1.0F);
                }
            }
        }
    }
}
