package com.empanadas.mixin;

import com.empanadas.Items.EmpanadaItem;
import com.empanadas.Items.ModItems;
import com.empanadas.events.CraftingEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.util.collection.DefaultedList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.LinkedList;
import java.util.List;

/*
*   Mixin que detecta que se crafteo una empanada
*/

@Mixin(CraftingResultSlot.class)
public abstract class RecipeMixin {

    @Unique
    private List<ItemStack> retornarIngredientes(CraftingRecipeInput craftingRecipeInput){
        LinkedList<ItemStack> res = new LinkedList<>();

        for(int i = 0; i < craftingRecipeInput.size(); ++i) {
            ItemStack item = craftingRecipeInput.getStackInSlot(i);
            if(!item.isIn(ItemTags.AXES) && (item.getItem() != ModItems.TAPA_EMPANADA) && (item.getItem() != Items.AIR)){
                res.add(item.copy());
            }
        }

        return res;
    }

    @Inject(
            method = "onTakeItem",
            at = @At("HEAD")
    )
    private void afterCraftCompleted(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if(stack.getItem() instanceof EmpanadaItem){
            CraftingMixingAccesor accessor = (CraftingMixingAccesor) (Object) this;
            RecipeInputInventory input = accessor.getInput();
            CraftingRecipeInput.Positioned positioned = input.createPositionedRecipeInput();
            CraftingRecipeInput craftingRecipeInput = positioned.input();

            List<ItemStack> res = retornarIngredientes(craftingRecipeInput);
            CraftingEvents.ON_CRAFT.invoker().interact(player,stack,res);
        }
    }

}

