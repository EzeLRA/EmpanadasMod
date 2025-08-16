package com.empanadas;

import com.empanadas.Items.ModItemGrops;
import com.empanadas.Items.ModItems;
import com.empanadas.component.ModComponents;
import com.empanadas.events.CraftingEvents;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmpanadasMod implements ModInitializer {
	public static final String MOD_ID = "empanadas-mod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGrops.registerItemGroups();
		ModItems.initialize();
		ModComponents.initialize();

        CraftingEvents.VALIDATE_CRAFT.register((stack) -> {
            System.out.println("Se crafteo una empanada");
            return true;
        });
	}

}