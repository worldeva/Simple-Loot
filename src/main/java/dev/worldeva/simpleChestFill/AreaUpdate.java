package dev.worldeva.simpleChestFill;

import org.bukkit.ChunkSnapshot;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Stack;

public class AreaUpdate implements CommandExecutor {
    public boolean onCommand(CommandSender commandSender
            ,Command command
            ,String label
            ,String[] args) {
        return false;
    }

    protected static class UpdateChests extends BukkitRunnable {
        World world;
        SimpleChestFill.area area;
        Stack<ChunkSnapshot> chunks = new Stack<>();
        CommandSender sender;

        /**@todo Optimize the everliving crap out of this**/
        UpdateChests(SimpleChestFill.area area, CommandSender sender) {
            this.area = area;
            this.sender = sender;
            this.world = area.min.getWorld();

            //Get snapshot of all chunks within radius
            int i = 0;
            for(int x = area.min.getChunk().getX(); x <= area.max.getChunk().getZ(); ++x)
            {
                for(int z = area.min.getChunk().getZ(); z <= area.max.getChunk().getZ(); ++z)
                {
                    i++;
                    this.chunks.push(world.getChunkAt(x, z).getChunkSnapshot());
                }
            }
            sender.sendMessage("Chunks scanned:");
            sender.sendMessage(Integer.toString(i));
        }

        //@Override
        public void run() {
            Stack<Location> chests = new Stack<>();
            //BlockData chest = Material.CHEST.createBlockData();

            for(ChunkSnapshot chunk : chunks) {
                if (true) { //chunk.contains
                    for (int x = 0; x < 16; ++x) {
                        for (int z = 0; z < 16; ++z) {
                            for (int y = area.min.getBlockY(); y <= area.min.getBlockY(); ++y) {
                                if (chunk.getBlockType(x, y, z) == Material.CHEST && //Check if is chest
                                    chunk.getZ()+z <= area.max.getBlockZ() && //Check if Below Highest Z
                                    chunk.getZ()+z >= area.min.getBlockZ() && //Check if Above Lowest Z
                                    chunk.getX()+x <= area.max.getBlockX() && //Check if Below Highest X
                                    chunk.getX()+x >= area.min.getBlockX() /*Check if Above Lowest X*/) {
                                    chests.push(new Location(world, x, y, z));
                                }
                            }
                        }
                    }
                }
            }

            (new GetContainers(chests, area, sender)).runTask(SimpleChestFill.getPlugin());
        }
        private static class GetContainers extends BukkitRunnable {
            Stack<Location> chests;
            CommandSender sender;
            SimpleChestFill.area area;

            GetContainers(Stack<Location> chests, SimpleChestFill.area area, CommandSender sender)
            {
                this.area = area;
                this.sender = sender;
                this.chests = chests;
            }
            //@Override
            public void run() {
                while(!chests.empty()) {
                    area.registeredContainers.add(chests.pop().getBlock());
                }
            }
        }
    }
}