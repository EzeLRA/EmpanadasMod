package com.empanadas.Items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.LinkedList;
import java.util.List;

//Objeto generico
public class EmpanadaItem extends Item {
    //Atributos
    private List<String> ingredientes;

    public EmpanadaItem(Settings settings){
        super(settings);
        this.ingredientes = new LinkedList<String>();
    }

    //Metodo para agregar un ingrediente
    public void ingresarIngrediente(String ingrediente){
        this.ingredientes.add(ingrediente);
    }

    //Metodo para agregar infomarcion en el tooltip
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        if(!this.ingredientes.isEmpty()) {
            tooltip.add(Text.translatable("itemTooltip.empanadas-mod.empanadaitem_conIngredientes"));
        }else{
            tooltip.add(Text.translatable("itemTooltip.empanadas-mod.empanadaitem"));
        }
    }
}
