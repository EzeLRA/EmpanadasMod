package com.empanadas;

import com.empanadas.Items.EmpanadaItem;
import com.empanadas.Items.ModItemGrops;
import com.empanadas.Items.ModItems;
import com.empanadas.component.ModComponents;
import com.empanadas.events.CraftingEvents;
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

public class EmpanadasMod implements ModInitializer {
	public static final String MOD_ID = "empanadas-mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGrops.registerItemGroups();
		ModItems.initialize();
		ModComponents.initialize();

		CraftingEvents.ON_CRAFT.register((player, stack, ingredients) -> {
			player.sendMessage(Text.literal(
					"Crafteaste " + stack.getName() + " con " + ingredients.size() + " ingredientes"
			), false);

			player.sendMessage(Text.literal("Ingredientes usados:"),false);

			for (ItemStack ingredient : ingredients) {
				player.sendMessage(Text.literal(
						"- " + ingredient.getCount() + "x " + ingredient.getName().getString()
				), false);
			}
		});
	}

}