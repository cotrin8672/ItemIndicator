package io.github.cotrin8672.itemindicator.render

import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.world.item.ItemStack

interface ItemOverlay {
    fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean
}

infix fun ItemOverlay.and(overlay: ItemOverlay): ItemOverlay {
    return object : ItemOverlay {
        override fun render(
            guiGraphics: GuiGraphics,
            textRenderer: Font,
            stack: ItemStack,
            xOffset: Int,
            yOffset: Int,
        ): Boolean {
            return this@and.render(guiGraphics, textRenderer, stack, xOffset, yOffset) &&
                    overlay.render(guiGraphics, textRenderer, stack, xOffset, yOffset)
        }
    }
}
