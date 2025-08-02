package com.empanadas;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.empanadas.Items.ModItems;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;

public class EmpanadasModRecipeProvider extends FabricRecipeProvider {
    public EmpanadasModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
                //Receta para Tapa de Empanadas
                createShapeless(RecipeCategory.FOOD, ModItems.TAPA_EMPANADA).input(Items.EGG).input(Items.WHEAT).input(Items.POTION)
                        .criterion(hasItem(Items.EGG),conditionsFromItem(Items.EGG))
                        .criterion(hasItem(Items.WHEAT),conditionsFromItem(Items.WHEAT))
                        .criterion(hasItem(Items.POTION),conditionsFromItem(Items.POTION))
                        .offerTo(exporter);
                //Receta para Empanada cruda(completar)
                createShapeless(RecipeCategory.FOOD, ModItems.EMPANADA_CRUDA).input(ModItems.TAPA_EMPANADA).input(ConventionalItemTags.COOKED_MEAT_FOODS).input(ConventionalItemTags.COOKED_FISH_FOODS).input(ConventionalItemTags.VEGETABLE_FOODS)
                        .criterion(hasItem(ModItems.TAPA_EMPANADA),conditionsFromItem(ModItems.TAPA_EMPANADA))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "FabricDocsReferenceRecipeProvider";
    }
}
