package com.empanadas;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class EmpanadasModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		//Paquete principal para agregar los proveedores
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(EmpanadasModRecipeProvider::new);
	}
}
