package dev.worldeva.simpleChestFill;

import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.SessionOwner;
import com.sk89q.worldedit.world.World;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.LocationArgument;
import dev.jorel.commandapi.arguments.LocationType;
import dev.jorel.commandapi.arguments.StringArgument;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.PlayerCommandExecutor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.loot.LootTable;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Stack;

/**@todo optimized the shittakes out of this**/
class AreaCreateCommandAPI implements dev.jorel.commandapi.executors.CommandExecutor {
    @Override
    public void run(CommandSender commandSender, Object[] args) throws WrapperCommandSyntaxException {

        SimpleChestFill.area area = new SimpleChestFill.area(
                (Location) args[1],
                (Location) args[2]);
        area.defaultLoot = (LootTable) args[3];
        SimpleChestFill.areas.put((String) args[0], area);
        (new AreaUpdate.UpdateChests(area, commandSender)).runTask(SimpleChestFill.getPlugin());
    }

    private static class WEHandler implements PlayerCommandExecutor {
        WorldEdit WE = WorldEdit.getInstance();
        @Override
        public void run(Player commandSender, Object[] args) throws WrapperCommandSyntaxException {
            Region selection = null;
            try {
                selection = WE.getSessionManager().get((SessionOwner) commandSender).getSelection((World) (commandSender).getWorld());
            }
            catch (Exception e)
            {
                commandSender.sendMessage("Selection not complete!");
            }
            SimpleChestFill.area area = new SimpleChestFill.area(new Location(
                    (org.bukkit.World) selection.getWorld(),
                    selection.getMinimumPoint().getX(),
                    selection.getMinimumPoint().getY(),
                    selection.getMinimumPoint().getZ()),
                    new Location(
                    (org.bukkit.World) selection.getWorld(),
                    selection.getMaximumPoint().getX(),
                    selection.getMaximumPoint().getY(),
                    selection.getMaximumPoint().getZ()));
            area.defaultLoot = (LootTable) args[1];
            SimpleChestFill.areas.put((String) args[0], area);
            (new AreaUpdate.UpdateChests(area, commandSender)).runTask(SimpleChestFill.getPlugin());
        }
    }

    static public List<CommandAPICommand> create(String name) {
        Stack<CommandAPICommand> ret = new Stack<>();

        Plugin WE = Bukkit.getPluginManager().getPlugin("WorldEdit");
        if(WE != null) {
            ret.push(new CommandAPICommand(name).executesPlayer(new WEHandler())
                    .withArguments(new StringArgument("name")
                            , new CommandAPICustomArgs.LootTableArg("loot table")));
        }
        ret.push(new CommandAPICommand(name).executes(new AreaCreateCommandAPI())
                .withArguments(new StringArgument("name")
                        , new LocationArgument("from",LocationType.BLOCK_POSITION)
                        , new LocationArgument("to",LocationType.BLOCK_POSITION)
                        , new CommandAPICustomArgs.LootTableArg("loot table")));
        return ret;
    }
}