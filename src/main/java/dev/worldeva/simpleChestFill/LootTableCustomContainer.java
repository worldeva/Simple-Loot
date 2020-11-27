package dev.worldeva.simpleChestFill;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;

import java.util.Collection;
import java.util.Random;

public class LootTableCustomContainer implements LootTable {
    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext lootContext) {
        return null;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext lootContext) {

    }

    @Override
    public NamespacedKey getKey() {
        return null;
    }
}
    /*public static class LootTableEntry
    {
        public enum Rarity {
            LEGENDARY,
            RARE,
            UNCOMMON,
            COMMON,
            JUNK
        }

        ItemStack item;
        float occuranceProbability;
        float occuranceWeight;
        int cost = 0;
        String group;
        float luckModifier = 0;

        LootTableEntry(Material item, int cost)
        {
            ConstructorHelper(item);
        }
        LootTableEntry(Material item, Rarity level)
        {
            switch(level)
            {
                case LEGENDARY:
                    ConstructorHelper(item);
                    break;
                case RARE:
                    ConstructorHelper(item);
                    break;
                case UNCOMMON:
                    ConstructorHelper(item);
                    break;
                case COMMON:
                    break;
                case JUNK:
                    ConstructorHelper(item);
                    break;
            }
        }
        private void ConstructorHelper(Material item)
        {
            this.item = new ItemStack(item);
        }
    }

    private NamespacedKey key;

    LootTableCustomContainer(String name, ArrayList<LootTableEntry>){
        this.key = new NamespacedKey(SimpleChestFill.getPlugin(), name);
    }

    @Override
    public Collection<ItemStack> populateLoot(Random random, LootContext lootContext) {
        return null;
    }

    @Override
    public void fillInventory(Inventory inventory, Random random, LootContext lootContext) {
        lootContext
    }

    @Override
    public NamespacedKey getKey() {
        return this.key;
    }*/
