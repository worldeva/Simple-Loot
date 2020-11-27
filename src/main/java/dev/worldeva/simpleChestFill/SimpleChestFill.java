package dev.worldeva.simpleChestFill;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class SimpleChestFill extends JavaPlugin {
    private static JavaPlugin plugin;
    public static class area {
        Location min;
        Location max;
        LootTable defaultLoot = LootTables.EMPTY.getLootTable();
        ArrayList<Block> registeredContainers = new ArrayList<>();

        area(Location min, Location max)
        {
            assert min.getWorld() == max.getWorld();
            this.min = min;
            this.max  = max;
        }
    }
    public static HashMap<String, area> areas = new HashMap<>();
    public static HashMap<String, LootTable> tables = new HashMap<>();
    private Stack<CommandAPICommand> reg = new Stack<>();

    @Override
    public void onEnable() {
        CommandAPI.onEnable(this);
        plugin = this;
        reg.push(new CommandRouter("fc")).register();
    }

    @Override
    public void onLoad() {
        CommandAPI.onLoad(true);
    }

    @Override
    public void onDisable() {
        while(!reg.empty())
        {
            CommandAPI.unregister(reg.pop().getName(), true);
        }
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static LootTable resolveLoot(String tableName)
    {
        if(tables.containsKey(tableName))
        {
            return tables.get(tableName);
        }
        else
        {
            try
            {

                return LootTables.valueOf(tableName).getLootTable();
            }
            catch (Exception e)
            {
                return null;
            }
        }
    }
}
