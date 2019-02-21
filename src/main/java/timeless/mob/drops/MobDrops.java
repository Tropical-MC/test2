/*
 * Copyright (c) 2019. This owned by DigitalBeehive LLC,
 * Any use of this is prohibited outside of DigitalBeehive owned
 * sites/servers.
 */

package timeless.mob.drops;

import com.vk2gpz.tokenenchant.api.EnchantInfo;
import com.vk2gpz.tokenenchant.api.PotionHandler;
import com.vk2gpz.tokenenchant.api.TokenEnchantAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDeathEvent;


public class MobDrops extends PotionHandler {

    public MobDrops(TokenEnchantAPI plugin) {
        super(plugin);
        loadConfig();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }

    public void loadConfig() {

        super.loadConfig();
    }

    public String getName() {
        return "MobDrops";
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onDeathEvent(EntityDeathEvent e) {

        double percentage = rand.nextDouble();
        final Entity entity = e.getEntity();
        if (((LivingEntity) entity).getKiller() == null) {
            return;
        }
        Double Chance = this.plugin.getConfig().getDouble("Potions." + getName() + ".chance");
        final Player killer = ((LivingEntity) entity).getKiller();
        if (e.getEntity().getKiller() != null) {
            Player p = e.getEntity().getKiller();
            EnchantInfo ei = hasPotionEffect(p, MobDrops.class);
            if (ei.getRealName() == "MobDrops") {
                if (percentage * ei.getLevel() > Chance) {

                    switch (e.getEntity().getType()) {
                        case BLAZE:
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 300");
                            break;
                        case SPIDER:
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 150");
                            break;
                        case ZOMBIE:
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 100");
                            break;
                        case SKELETON:
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 100");
                            break;
                        case CHICKEN:
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 75");
                            break;
                        case CREEPER:
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "eco give " + p.getName() + " 500");
                            break;
                        default:
                            return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            return;
        }
    }
}
