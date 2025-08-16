package com.empanadas.mixin;

import com.empanadas.Items.EmpanadaItem;
import com.empanadas.events.CraftingEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.screen.slot.CrafterOutputSlot;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
*   Mixin que detecta que se crafteo una empanada
*/

@Mixin(CrafterOutputSlot.class)
public class RecipeMixin {


    @Inject(
            method = "insertStack(Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;",
            at = @At("HEAD"),
            cancellable = true
    )
    private void onItemAttemptInsert(ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {

        if (!stack.isEmpty() && stack.getItem() instanceof EmpanadaItem) {
            boolean isValid = CraftingEvents.VALIDATE_CRAFT.invoker().validateCraft(stack);

            if (!isValid) {
                cir.setReturnValue(stack); // Cancela la operación
                cir.cancel();
            }
        }
    }
}

