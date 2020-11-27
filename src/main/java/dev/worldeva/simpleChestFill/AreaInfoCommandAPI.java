package dev.worldeva.simpleChestFill;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AreaInfoCommandAPI extends CommandAPICommand {
    public AreaInfoCommandAPI(String commandName) {
        super(commandName);
        this.executes(new executor()).withArguments(new CommandAPICustomArgs.AreaNameArg("area"));
    }
    private class executor implements CommandExecutor{
        @Override
        public void run(CommandSender commandSender, Object[] objects) throws WrapperCommandSyntaxException {
            SimpleChestFill.area area = (SimpleChestFill.areas.get(objects[0]));
            commandSender.sendMessage("Name: " + (String) objects[0]);
            commandSender.sendMessage("Loot table: "+area.defaultLoot.getKey().toString());
            commandSender.sendMessage("Min Coords: "+
                    area.min.getWorld().toString()+
                    ": ["+
                    area.min.getBlockX()+","+
                    area.min.getBlockY()+","+
                    area.min.getZ()+
                    "]");
            commandSender.sendMessage("Max Coords: "+
                    area.max.getWorld().toString()+
                    ": ["+
                    area.max.getBlockX()+","+
                    area.max.getBlockY()+","+
                    area.max.getZ()+
                    "]");
            commandSender.sendMessage("Chest Number: "+area.registeredContainers.size());
        }
    }
}
