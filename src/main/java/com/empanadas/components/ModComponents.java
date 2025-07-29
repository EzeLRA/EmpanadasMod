package com.empanadas.components;

import com.empanadas.EmpanadasMod;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModComponents {
    //Revisar
    public static final ComponentType<String> EMPANADA_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(EmpanadasMod.MOD_ID, "Sin ingredientes"),
            ComponentType.<Integer>builder().codec(null).build()
    );
    protected static void initialize() {

    }
}
