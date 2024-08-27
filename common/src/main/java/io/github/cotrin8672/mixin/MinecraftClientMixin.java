package io.github.cotrin8672.mixin;

import io.github.cotrin8672.render.BeeOverlayRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "render", at = @At("HEAD"))
    private void itemindicator$render(boolean tick, CallbackInfo ci) {
        BeeOverlayRenderer.INSTANCE.getBeeRenderTickCounter().beginRenderTick(Util.getMeasuringTimeMs(), tick);
    }
}
