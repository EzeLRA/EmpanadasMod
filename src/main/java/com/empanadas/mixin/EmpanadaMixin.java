package com.empanadas.mixin;

import com.empanadas.EmpanadasModItemTagProvider;
import com.empanadas.Items.ModItems;
import com.empanadas.component.ModComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;


@Mixin(PlayerEntity.class)
public class EmpanadaMixin {
    @Inject(
            method = "onRecipeCrafted",
            at = @At("HEAD")
    )
    private void onEmpanadaCrafted(RecipeEntry<?> recipe, List<ItemStack> ingredients, CallbackInfo ci) {
        recipe.value().getIngredientPlacement();
    }
}
