package com.empanadas.Items;

import com.empanadas.EmpanadasMod;
import com.empanadas.component.ModComponents;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.*;

//Objeto generico
public class EmpanadaItem extends Item {

    public EmpanadaItem(Settings settings){
        super(settings);
    }

    private void extraerNombresIngredientes(ItemStack stack,String ingredientsJson) {
        List<String> ingredientes = new LinkedList<String>();

        try {
            JsonArray jsonArray = JsonParser.parseString(ingredientsJson).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject obj = element.getAsJsonObject();
                if (obj.has("name")) {
                    ingredientes.add(obj.get("name").getAsString());
                }
            }
            stack.set(ModComponents.INGREDIENTES_LIST, ingredientes);

        } catch (Exception e) {
            EmpanadasMod.LOGGER.error("Error extrayendo nombres de ingredientes", e);
        }

    }
    //Metodo para agregar infomarcion en el tooltip
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        // Leer ingredientes del stack específico
        List<String> ingredientes = stack.get(ModComponents.INGREDIENTES_LIST);

        if (ingredientes != null) {

            if(!ingredientes.isEmpty()){
                // Mostrar en el tooltip
                tooltip.add(Text.literal("Tiene Ingredientes:").formatted(Formatting.GOLD));
                for(String ingrediente : ingredientes ){
                    tooltip.add(Text.literal(ingrediente));
                }
            }else{
                tooltip.add(Text.literal("Sin ingredientes").formatted(Formatting.RED));
            }
        } else {
            tooltip.add(Text.literal("En preparacion").formatted(Formatting.RED));
        }

        /*
        Adaptarlo a futuro para multiples idiomas
        if(!this.ingredientes.isEmpty()) {
            tooltip.add(Text.translatable("itemTooltip.empanadas-mod.empanadaitem_conIngredientes"));
            //Agregar los ingredientes que tenga en la lista
        }else{
            tooltip.add(Text.translatable("itemTooltip.empanadas-mod.empanadaitem"));
        }
        */
    }


    @Override
    public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
        // Llamar a la implementación original primero
        super.onCraftByPlayer(stack, world, player);

        if (!world.isClient) {
            // Verificar si tiene ingredientes disponibles
            boolean tieneIngredientes = stack.getOrDefault(ModComponents.INGREDIENTES_DISPONE, false);

            if (tieneIngredientes) {
                // Obtener y parsear los ingredientes
                String ingredientesJson = stack.get(ModComponents.INGREDIENTES);
                if (!ingredientesJson.isEmpty()) {
                    this.extraerNombresIngredientes(stack,ingredientesJson);
                }
            }
            stack.remove(ModComponents.INGREDIENTES);
            stack.set(ModComponents.INGREDIENTES_DISPONE, false);

        }
    }
}
