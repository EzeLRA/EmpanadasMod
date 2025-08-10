package com.empanadas.Items;

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
        if(!this.ingredientes.isEmpty()) {
            tooltip.add(Text.translatable("itemTooltip.empanadas-mod.empanadaitem_conIngredientes"));
            //Agregar los ingredientes que tenga en la lista
        }else{
            tooltip.add(Text.translatable("itemTooltip.empanadas-mod.empanadaitem"));
        }
    }

    // Método para agregar ingredientes
    public static void addIngredient(ItemStack stack, RegistryEntry<Item> ingredientEntry) {
        if (stack.getItem() instanceof EmpanadaItem) {
            // Obtener el lore existente o crear uno nuevo
            LoreComponent loreComponent = stack.getOrDefault(DataComponentTypes.LORE,
                    new LoreComponent(new ArrayList<>()));

            List<Text> loreLines = new ArrayList<>(loreComponent.lines());

            // Crear texto para el nuevo ingrediente
            Text ingredientText = Text.literal(" • ").append(ingredientEntry.value().getName())
                    .formatted(Formatting.DARK_GRAY);

            if (!loreLines.contains(ingredientText)) {
                // Añadir encabezado si es el primer ingrediente
                if (loreLines.isEmpty()) {
                    loreLines.add(Text.translatable("tooltip.empanadas.ingredients")
                            .formatted(Formatting.GRAY));
                }

                loreLines.add(ingredientText);

                // Actualizar el componente LORE correctamente
                stack.set(DataComponentTypes.LORE, new LoreComponent(loreLines));
            }
        }
    }

    // Método alternativo que acepta Item directamente
    public static void addIngredient(ItemStack stack, Item ingredient) {
        addIngredient(stack, Registries.ITEM.getEntry(ingredient));
    }

    // Método para registrar ingredientes al craftear
    public static void registerCraftingIngredients(ItemStack empanadaStack, Collection<ItemStack> inputs) {
        for (ItemStack input : inputs) {
            if (!input.isEmpty()) {
                addIngredient(empanadaStack, input.getItem());
            }
        }
    }

    @Override
    public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
        super.onCraftByPlayer(stack, world, player);
        // La lógica de ingredientes ahora se maneja externamente
    }
}
