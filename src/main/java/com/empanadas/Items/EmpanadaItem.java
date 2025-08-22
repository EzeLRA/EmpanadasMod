package com.empanadas.Items;

import com.empanadas.component.ModComponents;
import net.minecraft.component.Component;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.LoreComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.apache.commons.io.input.TaggedInputStream;

import java.util.*;

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

        // Verificar si tiene ingredientes disponibles
        boolean tieneIngredientes = stack.getOrDefault(ModComponents.INGREDIENTES_DISPONE, false);

        if (tieneIngredientes) {
            // Obtener y parsear los ingredientes
            String ingredientesJson = stack.get(ModComponents.INGREDIENTES);
            /*
            *   Se debera procesar la cadena por el cual extraer solo el nombre [{"name":"ingre1"},{"name":"ingre2"}]
            */

            if (!ingredientesJson.isEmpty()) {

                // Mostrar en el tooltip
                tooltip.add(Text.literal("Tiene Ingredientes:").formatted(Formatting.GOLD));
                tooltip.add(Text.literal(ingredientesJson));

            }
        } else {
            tooltip.add(Text.literal("Sin Ingredientes").formatted(Formatting.RED));
        }

        /*
        if(!this.ingredientes.isEmpty()) {
            tooltip.add(Text.translatable("itemTooltip.empanadas-mod.empanadaitem_conIngredientes"));
            //Agregar los ingredientes que tenga en la lista
        }else{
            tooltip.add(Text.translatable("itemTooltip.empanadas-mod.empanadaitem"));
        }

        */
    }

    /*

    Se debera considerar este metodo para que se mantenga el tooltip en el caso de hornearse u otro proceso

    @Override
    public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
        // Llamar a la implementación original primero
        super.onCraftByPlayer(stack, world, player);

        if (!world.isClient) {

            // Lógica solo en el servidor
            player.sendMessage(Text.literal("¡(1)Has crafteado " + stack.getName().getString() + "!"), false);



        }
    }
    */

}
