package com.empanadas.component;

import com.empanadas.EmpanadasMod;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModComponents {

    public static final ComponentType<Boolean> INGREDIENTES_DISPONE = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(EmpanadasMod.MOD_ID, "ingredientes_dispone"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );
    public static void initialize() {
        //El ComponentType "EMPANADA_COMPONENT" implementara la funcionalidad de guardar y escribir los ingredientes que se usen en el crafteo de la empanada
    }
}
