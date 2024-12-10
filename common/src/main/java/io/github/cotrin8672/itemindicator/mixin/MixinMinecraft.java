package io.github.cotrin8672.itemindicator.mixin;

import io.github.cotrin8672.itemindicator.render.BeeOverlayRenderer;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(method = "runTick", at = @At("HEAD"))
    private void itemindicator$render(boolean tick, CallbackInfo ci) {
        BeeOverlayRenderer.INSTANCE.getBeeRenderTickCounter().advanceTime(Util.getMillis(), tick);
    }
}
