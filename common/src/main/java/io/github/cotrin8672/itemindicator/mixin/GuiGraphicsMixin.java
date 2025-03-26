package io.github.cotrin8672.itemindicator.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cotrin8672.itemindicator.render.ItemOverlay;
import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiGraphics.class)
public class GuiGraphicsMixin {
    @Inject(
            method = "renderItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V",
            at = @At("RETURN")
    )
    private void itemindicator$renderItemDecorations(
            Font font,
            ItemStack itemStack,
            int i,
            int j,
            String string,
            CallbackInfo ci
    ) {
        itemindicator$resetRenderState();

        ItemOverlay renderer = ItemOverlayHandler.getOverlay(itemStack.getItem());
        if (renderer != null) {
            renderer.render((GuiGraphics) (Object) this, font, itemStack, i, j);
        }
    }

    @Unique
    private void itemindicator$resetRenderState() {
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
    }
}
