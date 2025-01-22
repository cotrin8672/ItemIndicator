package io.github.cotrin8672.itemindicator.util

import com.mojang.blaze3d.platform.Lighting
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

fun GuiGraphics.renderItemModel(
    x: Float,
    y: Float,
    scale: Float,
    stack: ItemStack,
) {
    val minecraft = Minecraft.getInstance()
    val model = minecraft.itemRenderer.getModel(stack, minecraft.level, minecraft.player, 16777216)
    pose().withMatrixContext {
        translate(x, y, 160f)
        scale(scale, -scale, scale)
        val flag = !model.usesBlockLight()
        if (flag) Lighting.setupForFlatItems()

        minecraft.itemRenderer.render(
            stack,
            ItemDisplayContext.GUI,
            false,
            this,
            bufferSource(),
            15728880,
            OverlayTexture.NO_OVERLAY,
            model
        )
        flush()
        if (flag) Lighting.setupFor3DItems()
    }
}
