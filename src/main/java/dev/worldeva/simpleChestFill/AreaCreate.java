package dev.worldeva.simpleChestFill;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.loot.LootTable;

public class AreaCreate implements CommandExecutor{
    //@Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player)
        {
            SimpleChestFill.area area = new SimpleChestFill.area(
                    new Location(((Player)commandSender).getWorld(),
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]),
                            Integer.parseInt(args[3])),
                    new Location(((Player)commandSender).getWorld(),
                            Integer.parseInt(args[4]),
                            Integer.parseInt(args[5]),
                            Integer.parseInt(args[6])));

            LootTable test = SimpleChestFill.resolveLoot(args[7]);
            area.defaultLoot = test;
            SimpleChestFill.areas.put(args[0], area);
            (new AreaUpdate.UpdateChests(area, commandSender)).runTask(SimpleChestFill.getPlugin());
            return true;
        }
        return false;
    }
}