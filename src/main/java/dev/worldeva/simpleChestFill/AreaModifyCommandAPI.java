package dev.worldeva.simpleChestFill;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.LocationType;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.loot.LootTable;

public class AreaModifyCommandAPI extends CommandAPICommand {
    public AreaModifyCommandAPI(String commandName) {
        super(commandName);
        this.withSubcommand(new CommandAPICommand("min")
                .executes(new min())
                .withArguments(new CommandAPICustomArgs.AreaArg("area"),
                        new LocationArgument("new min", LocationType.BLOCK_POSITION)))
            .withSubcommand(new CommandAPICommand("max")
                .executes(new max())
                .withArguments(new CommandAPICustomArgs.AreaArg("area"),
                        new LocationArgument("new max", LocationType.BLOCK_POSITION)))
            .withSubcommand(new CommandAPICommand("loot")
                .executes(new loot())
                .withArguments(new CommandAPICustomArgs.AreaArg("area"),
                        new CommandAPICustomArgs.LootTableArg("new loot")));
    }

    private class min implements CommandExecutor {
        @Override
        public void run(CommandSender commandSender, Object[] objects) throws WrapperCommandSyntaxException {
            if(((SimpleChestFill.area) objects[0]).min.getWorld() == ((Location) objects[1]).getWorld())
            {
                ((SimpleChestFill.area) objects[0]).min = ((Location) objects[1]);
            }
            commandSender.sendMessage("Failed: Locations not in same world!");
        }
    }

    private class max implements CommandExecutor {
        @Override
        public void run(CommandSender commandSender, Object[] objects) throws WrapperCommandSyntaxException {
            if(((SimpleChestFill.area) objects[0]).max.getWorld() == ((Location) objects[1]).getWorld())
            {
                ((SimpleChestFill.area) objects[0]).max = ((Location) objects[1]);
            }
            commandSender.sendMessage("Failed: Locations not in same world!");
        }
    }

    private class loot implements CommandExecutor {
        @Override
        public void run(CommandSender commandSender, Object[] objects) throws WrapperCommandSyntaxException {
            ((SimpleChestFill.area) objects[0]).defaultLoot = (LootTable) objects[1];
        }
    }
}
