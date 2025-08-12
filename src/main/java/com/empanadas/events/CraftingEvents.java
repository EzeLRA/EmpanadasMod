package com.empanadas.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.Identifier;

public interface CraftingEvents {
    Event<CraftingCallback> EVENT = EventFactory.createArrayBacked(
            CraftingCallback.class,
            (listeners) -> (player, stack, recipe, screenHandler) -> {
                for (CraftingCallback listener : listeners) {
                    listener.onCraft(player, stack, recipe, screenHandler);
                }
            }
    );

    @FunctionalInterface
    interface CraftingCallback {
        void onCraft(PlayerEntity player, ItemStack craftedStack, Recipe<?> recipe, ScreenHandler screenHandler);
    }
}
