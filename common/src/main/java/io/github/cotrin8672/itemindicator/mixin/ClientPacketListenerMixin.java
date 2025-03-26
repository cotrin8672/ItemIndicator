package io.github.cotrin8672.itemindicator.mixin;

import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.common.ClientboundUpdateTagsPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(method = "handleUpdateTags", at = @At("TAIL"))
    private void itemindicator$handleUpdateTags(ClientboundUpdateTagsPacket clientboundUpdateTagsPacket, CallbackInfo ci) {
        ItemOverlayHandler.remapOverlay();
    }
}
