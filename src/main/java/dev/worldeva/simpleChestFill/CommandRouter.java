package dev.worldeva.simpleChestFill;

import dev.jorel.commandapi.CommandAPICommand;

public class CommandRouter extends CommandAPICommand {
    public CommandRouter(String commandName) {
        super(commandName);
        CommandAPICommand area = new CommandAPICommand("area");

        for(CommandAPICommand sub : AreaCreateCommandAPI.create("add"))
        {
            area.withSubcommand(sub);
        }

        for(CommandAPICommand sub : AreaFillChestsCommandAPI.create("fill"))
        {
            area.withSubcommand(sub);
        }

        area.withSubcommand(new AreaDeleteCommandAPI("delete"))
            .withSubcommand(new AreaModifyCommandAPI("modify"))
            .withSubcommand(new AreaInfoCommandAPI("info"));

        this.withSubcommand(area);

        this.withSubcommand(new CommandAPICommand("loot")
                .withSubcommand(new CommandAPICommand("add"))
                .withSubcommand(new CommandAPICommand("delete"))
                .withSubcommand(new CommandAPICommand("modify"))
                .withSubcommand(new CommandAPICommand("info")));
    }
}