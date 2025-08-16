// CraftingEvents.java
package com.empanadas.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;

public interface CraftingEvents {
    Event<CraftingValidator> VALIDATE_CRAFT = EventFactory.createArrayBacked(
            CraftingValidator.class,
            callbacks -> stack -> {
                for (CraftingValidator callback : callbacks) {
                    if (!callback.validateCraft(stack)) {
                        return false; // Invalidación inmediata
                    }
                }
                return true; // Validación exitosa
            });

    @FunctionalInterface
    interface CraftingValidator {
        boolean validateCraft(ItemStack stack);
    }
}
