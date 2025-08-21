// CraftingEvents.java
package com.empanadas.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;

public interface CraftingEvents {
    Event<CraftingEventos> ON_CRAFT = EventFactory.createArrayBacked(CraftingEventos.class,
            (listeners) -> (player,stack,ingredients) -> {
                for(CraftingEventos listener : listeners){
                    listener.interact(player,stack,ingredients);
                    //ActionResult result = listener.interact(player,stack,ingredients,recipe);
                    //if(result != ActionResult.PASS){
                    //    return result;
                    //}
                }
                //return ActionResult.PASS;
            });

    //ActionResult interact(PlayerEntity player, ItemStack stack, List<ItemStack> ingredients, Recipe<?> recipe);
    @FunctionalInterface
    interface CraftingEventos {
        void interact(PlayerEntity player, ItemStack stack,
                             List<ItemStack> ingredients);
    }
}
