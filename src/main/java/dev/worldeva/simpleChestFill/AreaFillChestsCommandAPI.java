package dev.worldeva.simpleChestFill;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.command.CommandSender;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;

public class AreaFillChestsCommandAPI {
    public static CommandAPICommand[] create(String commandName) {
        return new CommandAPICommand[]{
                new CommandAPICommand(commandName).executes(new executorDefault())
                        .withArguments(new CommandAPICustomArgs.AreaArg("area")),
                new CommandAPICommand(commandName).executes(new executor())
                        .withArguments(new CommandAPICustomArgs.LootTableArg("loot table"))
        };
    }
    private static class executorDefault implements CommandExecutor {
        @Override
        public void run(CommandSender commandSender, Object[] objects) throws WrapperCommandSyntaxException {
            for(Block chest : ((SimpleChestFill.area) objects[0]).registeredContainers)
            {
                BlockState state = chest.getState();
                ((Lootable) state).setLootTable(((SimpleChestFill.area) objects[0]).defaultLoot);
                state.update();
            }
        }
    }
    private static class executor implements CommandExecutor {
        @Override
        public void run(CommandSender commandSender, Object[] objects) throws WrapperCommandSyntaxException {
            for(Block chest : ((SimpleChestFill.area) objects[0]).registeredContainers)
            {
                BlockState state = chest.getState();
                ((Lootable) state).setLootTable((LootTable) objects[1]);
                state.update();
            }
        }
    }
}
