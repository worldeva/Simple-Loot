package dev.worldeva.simpleChestFill;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;

public class AreaFillChests implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender,Command command, String label, String[] args) {
        if(SimpleChestFill.areas.containsKey(args[0]))
        {
            SimpleChestFill.area area = SimpleChestFill.areas.get(args[0]);
            LootTable loot = area.defaultLoot;
            if(args.length==2)
            {
                LootTable tloot = SimpleChestFill.resolveLoot(args[1]);
                if(tloot != null)
                {
                    loot = tloot;
                }
            }
            int i = 0;
            for(Block chest : area.registeredContainers)
            {
                BlockState state = chest.getState();
                ((Lootable)state).setLootTable(loot);
                (state).update();
                ++i;
            }
            commandSender.sendMessage(Integer.toString(i));
            return true;
        }
        else
        {
            commandSender.sendMessage("Not a valid area");
        }
        return false;
    }
}