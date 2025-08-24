package com.empanadas.mixin;

import com.empanadas.Items.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.slot.CraftingResultSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
    Averiguar bug : Se Duplican los items usados en la receta si se usan mas de uno
*/

@Mixin(CraftingResultSlot.class)
public abstract class NoConsumeMixin {

    @Inject(method = "onTakeItem", at = @At("HEAD"))
    private void preventItemConsumption(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (stack.getItem() == ModItems.TAPA_EMPANADA) {
            // Buscar "Potion" en el inventario de crafteo y devolverlo
            CraftingMixingAccesor accessor = (CraftingMixingAccesor) (Object) this;
            RecipeInputInventory inventory = accessor.getInput();

            for (int i = 0; i < inventory.size(); i++) {
                ItemStack slotStack = inventory.getStack(i);
                if (slotStack.isOf(Items.POTION)) {
                    // Devolver el item al inventario del jugador
                    player.getInventory().offerOrDrop(Items.GLASS_BOTTLE.getDefaultStack());    //Probar
                    inventory.setStack(i, ItemStack.EMPTY);
                }
            }
        }
    }

    @Inject(method = "onTakeItem", at = @At("HEAD"))
    private void preventAxeConsumption(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (stack.getItem() == ModItems.EMPANADA_CRUDA) {
            // Buscar hachas en el inventario de crafteo y devolverlas
            preventItemsConsumption(player, ItemTags.AXES);
        }
    }

    @Unique
    private void preventItemsConsumption(PlayerEntity player, TagKey<Item> itemTag) {
        CraftingMixingAccesor accessor = (CraftingMixingAccesor) (Object) this;
        RecipeInputInventory inventory = accessor.getInput();

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack slotStack = inventory.getStack(i);
            if (slotStack.isIn(itemTag)) {
                // Devolver el item al inventario del jugador
                player.getInventory().offerOrDrop(slotStack.copy());
                inventory.setStack(i, ItemStack.EMPTY);
            }
        }

    }
}
