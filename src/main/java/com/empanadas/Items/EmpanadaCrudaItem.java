package com.empanadas.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class EmpanadaCrudaItem extends Item {
    public EmpanadaCrudaItem (Settings settings){
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        //Implementacion de muestra de ingredientes para la empanada

        super.appendTooltip(stack, context, tooltip, type);
    }

}
