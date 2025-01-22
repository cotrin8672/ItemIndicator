package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.util.renderItemModel
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items

object WaxedOverlayRenderer : ItemOverlay {
    private val honeycomb = ItemStack(Items.HONEYCOMB)

    override fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        if (!ItemIndicator.CONFIG.renderWaxedIconOverlay) return false
        guiGraphics.renderItemModel(xOffset + 12f, yOffset + 4f, 6f, honeycomb)

        return true
    }
}
