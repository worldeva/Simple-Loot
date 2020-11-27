package dev.worldeva.gamPlugin;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffectType;

public class gamPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new fireResistLightning(), this);
    }
}

class fireResistLightning implements Listener {
    @EventHandler
    public void onPlayerLightning(EntityDamageEvent e) {
        if(e.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING))
        {
            if(e.getEntityType().equals(EntityType.PLAYER)&&((Player)e.getEntity()).hasPotionEffect(PotionEffectType.FIRE_RESISTANCE))
                e.setCancelled(true);
        }
    }
}