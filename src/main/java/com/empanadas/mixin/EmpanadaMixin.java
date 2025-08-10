package com.empanadas.mixin;


import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;


@Mixin(PlayerEntity.class)
public class EmpanadaMixin {
    @Override
    public String toString() {
        return super.toString();
    }
}
