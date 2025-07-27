package com.empanadas.Items;

import com.empanadas.EmpanadasMod;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class ModItems {
    // Caracteristicas de la empanada (Settings)
    public static final FoodComponent EMPANADA_FOOD_COMPONENT = new FoodComponent.Builder().nutrition(5).build();
    // Instancias Items (Empanadas)
    public static final Item TAPA_EMPANADA = register("tapa_empanada", Item::new, new Item.Settings());
    public static final Item EMPANADA_CRUDA = register("empanada_cruda", Item::new, new Item.Settings());
    public static final Item EMPANADA_HORNEADA = register("empanada_horneada", Item::new, new Item.Settings().food(EMPANADA_FOOD_COMPONENT));
    public static final Item EMPANADA_FRITA = register("empanada_frita", Item::new, new Item.Settings().food(EMPANADA_FOOD_COMPONENT));

    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        /*
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.add(TAPA_EMPANADA);
            entries.add(EMPANADA_CRUDA);
            entries.add(EMPANADA_HORNEADA);
            entries.add(EMPANADA_FRITA);
        });
        */
    }




    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(EmpanadasMod.MOD_ID, name));

        // Create the item instance.
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        // Register the item.
        Registry.register(Registries.ITEM, itemKey, item);

        return item;
    }
}
