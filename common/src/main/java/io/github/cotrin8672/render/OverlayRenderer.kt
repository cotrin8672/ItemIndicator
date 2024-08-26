package io.github.cotrin8672.render

import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.item.ItemStack

interface OverlayRenderer {
    fun render(
        guiGraphics: DrawContext,
        textRenderer: TextRenderer,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean
}
