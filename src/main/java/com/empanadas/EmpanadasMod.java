package com.empanadas;

import com.empanadas.Items.EmpanadaItem;
import com.empanadas.Items.ModItemGrops;
import com.empanadas.Items.ModItems;
import com.empanadas.component.ModComponents;
import com.empanadas.events.CraftingEvents;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeMatcher;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ItemEvent;
import java.util.List;

public class EmpanadasMod implements ModInitializer {
	public static final String MOD_ID = "empanadas-mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private String convertIngredientsToJson(List<ItemStack> ingredients) {
		JsonArray jsonArray = new JsonArray();

		for (ItemStack ingredient : ingredients) {
			if (!ingredient.isEmpty()) {
				JsonObject jsonIngredient = new JsonObject();
				//jsonIngredient.addProperty("item", Registry.ITEM.getId(ingredient.getItem()).toString());
				//jsonIngredient.addProperty("count", ingredient.getCount());
				jsonIngredient.addProperty("name", ingredient.getName().getString());
				jsonArray.add(jsonIngredient);
			}
		}

		return jsonArray.toString();
	}

	@Override
	public void onInitialize() {
		ModItemGrops.registerItemGroups();
		ModItems.initialize();
		ModComponents.initialize();

		CraftingEvents.ON_CRAFT.register((player, stack, ingredients) -> {
			if (stack.getItem() instanceof EmpanadaItem) {

				// Convertir la lista de ingredientes a JSON string
				String ingredientsJson = convertIngredientsToJson(ingredients);

				// Guardar en el componente INGREDIENTES
				stack.set(ModComponents.INGREDIENTES, ingredientsJson);

				// Marcar que tiene ingredientes disponibles
				stack.set(ModComponents.INGREDIENTES_DISPONE, true);

			}
			/*
			player.sendMessage(Text.literal(
					"Crafteaste " + stack.getName() + " con " + ingredients.size() + " ingredientes"
			), false);

			player.sendMessage(Text.literal("Ingredientes usados:"),false);

			for (ItemStack ingredient : ingredients) {
				player.sendMessage(Text.literal(
						"- " + ingredient.getCount() + "x " + ingredient.getName().getString()
				), false);
			}*/
		});
	}

}