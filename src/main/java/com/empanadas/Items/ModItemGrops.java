package com.empanadas.Items;

import com.empanadas.EmpanadasMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGrops {
    public static final ItemGroup MENU_EMPANADAS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(EmpanadasMod.MOD_ID,"empanadas_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.EMPANADA_FRITA))
                    .displayName(Text.translatable("itemgroup.empanadas-mod.empanadas_items"))
                    .entries(((displayContext, entries) -> {
                        entries.add(ModItems.TAPA_EMPANADA);
                        entries.add(ModItems.EMPANADA_CRUDA);
                        entries.add(ModItems.EMPANADA_HORNEADA);
                        entries.add(ModItems.EMPANADA_FRITA);
                    }))
                    .build());

    public static void registerItemGroups(){

    }
}
