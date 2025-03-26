package io.github.cotrin8672.itemindicator.mixin;

import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler;
import net.minecraft.client.multiplayer.TagCollector;
import net.minecraft.core.RegistryAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TagCollector.class)
public class TagCollectorMixin {
    @Inject(method = "updateTags", at = @At("TAIL"))
    private void itemindicator$updateTags(RegistryAccess registryAccess, boolean bl, CallbackInfo ci) {
        ItemOverlayHandler.remapOverlay();
    }
}
