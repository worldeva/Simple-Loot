package dev.worldeva.simpleChestFill;

import dev.jorel.commandapi.arguments.CustomArgument;
import org.bukkit.NamespacedKey;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;

import java.util.Arrays;
import java.util.stream.Stream;

public class CommandAPICustomArgs {
    public static class AreaNameArg extends CustomArgument<String> {
        public AreaNameArg(String nodeName) {
            super(nodeName, (input)->{
                if(SimpleChestFill.areas.containsKey(input))
                {
                    return input;
                }
                throw new CustomArgument.CustomArgumentException(new CustomArgument.MessageBuilder("Not a existing area: ").appendArgInput());
            });
            this.overrideSuggestions((sender)-> {
                return (String[]) SimpleChestFill.areas.keySet().toArray();
            });
        }
    }

    public static class AreaArg extends CustomArgument<SimpleChestFill.area> {
        public AreaArg(String nodeName) {
            super(nodeName, (input)->{
                if(SimpleChestFill.areas.containsKey(input))
                {
                    return SimpleChestFill.areas.get(input);
                }
                throw new CustomArgument.CustomArgumentException(new CustomArgument.MessageBuilder("Not a existing area: ").appendArgInput());
            });
            this.overrideSuggestions((sender)-> {
                return (String[]) SimpleChestFill.areas.keySet().toArray();
            });
        }
    }

    public static class LootTableArg extends  CustomArgument<LootTable> {
        public LootTableArg(String nodeName) {
            super(nodeName, (input)->{
                org.bukkit.loot.LootTable table = null;
                String key = input.substring(input.indexOf(":")+1);
                if(SimpleChestFill.tables.containsKey(key))
                {
                    return SimpleChestFill.tables.get(key);
                }
                else
                {
                    for(LootTables o : LootTables.values())
                    {
                        if(o.getKey().toString().equals(input))
                        {
                            return o.getLootTable();
                        }
                    }
                }

                throw new CustomArgument.CustomArgumentException(new CustomArgument
                        .MessageBuilder("Invalid Loot Table: ").appendArgInput());
            }, true);
            this.overrideSuggestions((sender)->{
            /*ArrayList<String> keys = new ArrayList<>();
            for (LootTables o : LootTables.values()) {
                keys.add(o.getKey().getKey());
            }

            SimpleChestFill.tables.values().forEach((val)->{
                keys.add(val.getKey().getKey());
                });*/
                return Stream.concat(Arrays.stream(LootTables.values()).map(LootTables::getKey), SimpleChestFill.tables.values().stream().map(LootTable::getKey)).map(NamespacedKey::toString).toArray(String[]::new);// (String[]) keys.toArray();
            });
        }
    }
}
