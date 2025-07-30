package com.empanadas.component;

import com.empanadas.EmpanadasMod;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {

    public static final ComponentType<String> EMPANADA_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(EmpanadasMod.MOD_ID, "empanada_component"),
            ComponentType.<String>builder().codec(Codec.STRING).build()
    );
    public static void initialize() {
        //El ComponentType "EMPANADA_COMPONENT" implementara la funcionalidad de guardar y escribir los ingredientes que se usen en el crafteo de la empanada
    }
}
