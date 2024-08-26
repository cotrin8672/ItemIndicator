package io.github.cotrin8672.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cotrin8672.render.OverlayRenderer;
import io.github.cotrin8672.render.OverlayRendererRegistry;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.List;

@Mixin(DrawContext.class)
public class MixinDrawContext {
    @Inject(method = "drawItemInSlot(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("RETURN"))
    private void itemindicator$drawItemInSlot(TextRenderer textRenderer, ItemStack stack, int x, int y, String countOverride, CallbackInfo ci) {
        List<OverlayRenderer> renderers = OverlayRendererRegistry.INSTANCE.getItemOverlayRenderer(stack.getItem());
        if (renderers != null) {
            for (OverlayRenderer renderer : renderers) {
                if (renderer.render((DrawContext) (Object) this, textRenderer, stack, x, y)) {
                    itemindicator$resetRenderState();
                }
            }
        }
    }

    @Unique
    private void itemindicator$resetRenderState() {
        RenderSystem.enableDepthTest();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
    }
}
