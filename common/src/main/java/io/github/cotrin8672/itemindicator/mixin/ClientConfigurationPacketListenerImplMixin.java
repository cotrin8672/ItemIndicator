package io.github.cotrin8672.itemindicator.mixin;

import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler;
import net.minecraft.client.multiplayer.ClientConfigurationPacketListenerImpl;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.packs.resources.ResourceProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientConfigurationPacketListenerImpl.class)
public class ClientConfigurationPacketListenerImplMixin {
    @Inject(method = "method_57043", at = @At("RETURN"))
    private void itemindicator$remapOverlay(
            ResourceProvider resourceProvider,
            CallbackInfoReturnable<RegistryAccess.Frozen> cir
    ) {
        ItemOverlayHandler.remapOverlay();
    }
}
