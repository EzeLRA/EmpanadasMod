package com.empanadas.mixin;

import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.screen.slot.CraftingResultSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

/*
*  Accesor para acceder a "input" de la receta "en crudo"
*/

@Mixin(CraftingResultSlot.class)
public interface CraftingMixingAccesor {
    @Accessor("input")
    RecipeInputInventory getInput();
}
