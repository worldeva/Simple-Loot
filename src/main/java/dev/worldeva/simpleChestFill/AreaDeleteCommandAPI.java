package dev.worldeva.simpleChestFill;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AreaDeleteCommandAPI extends CommandAPICommand {
    @SuppressWarnings("unchecked")
    public AreaDeleteCommandAPI(String commandName) {
        super(commandName);
        this.executes(new executor()).withArguments(new CommandAPICustomArgs.AreaNameArg("area name"));
    }

    private class executor implements CommandExecutor {
        @Override
        public void run(CommandSender commandSender, Object[] objects) throws WrapperCommandSyntaxException {
            SimpleChestFill.areas.remove(objects[0]);
            commandSender.sendMessage("Area "+objects[0]+"removed successfully");
        }
    }
}
