package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.util.getContainerInfo
import io.github.cotrin8672.itemindicator.util.renderItemModel
import io.github.cotrin8672.itemindicator.util.withMatrixContext
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.RenderType
import net.minecraft.world.item.ItemStack

object ShulkerBoxOverlayRenderer : ItemOverlay {
    override fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        if (!ItemIndicator.CONFIG.renderShulkerBoxOverlay) return false
        val (displayStacks, fillLevel) = stack.getContainerInfo()
        when (displayStacks.size) {
            1 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 1f, yOffset + 6f, 0.6f, displayStacks.elementAt(0))
                }
            }

            2 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 1f, yOffset + 9f, 0.35f, displayStacks.elementAt(0))
                    renderItemModel(xOffset + 7f, yOffset + 9f, 0.35f, displayStacks.elementAt(1))
                }
            }

            3 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 1f, yOffset + 9f, 0.35f, displayStacks.elementAt(0))
                    renderItemModel(xOffset + 7f, yOffset + 9f, 0.35f, displayStacks.elementAt(1))
                    renderItemModel(xOffset + 4f, yOffset + 3f, 0.35f, displayStacks.elementAt(2))
                }
            }
        }

        if (fillLevel > 0) {
            guiGraphics.pose().withMatrixContext {
                val barXStart = xOffset + 13
                val barYStart = yOffset + 15
                val barXEnd = barXStart + 2
                val barYEnd = barYStart - 12
                guiGraphics.fill(RenderType.guiOverlay(), barXStart, barYStart, barXEnd, barYEnd, -16777216)
                guiGraphics.fill(
                    RenderType.guiOverlay(),
                    barXStart,
                    barYStart,
                    barXEnd - 1,
                    barYStart - (12 * fillLevel).toInt(),
                    (0xFF06b9bcL and 0xFFFFFFFFL).toInt()
                )
            }
        }

        return true
    }
}
