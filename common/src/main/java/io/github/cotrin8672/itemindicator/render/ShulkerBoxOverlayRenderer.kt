package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.util.getContainerInfo
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
        if (!ItemIndicator.CONFIG.shulkerOverlayConfig.renderShulkerOverlay) return false
        val (displayStacks, fillLevel) = stack.getContainerInfo()
        when (displayStacks.size) {
            1 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 1.5f, yOffset + 7f, 0.5f, displayStacks.elementAt(0))
                }
            }

            2 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 0.5f, yOffset + 10f, 0.35f, displayStacks.elementAt(0))
                    renderItemModel(xOffset + 6.5f, yOffset + 10f, 0.35f, displayStacks.elementAt(1))
                }
            }

            3 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 3.5f, yOffset + 4f, 0.35f, displayStacks.elementAt(0))
                    renderItemModel(xOffset + 0.5f, yOffset + 10f, 0.35f, displayStacks.elementAt(1))
                    renderItemModel(xOffset + 6.5f, yOffset + 10f, 0.35f, displayStacks.elementAt(2))
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

    private fun GuiGraphics.renderItemModel(
        x: Float,
        y: Float,
        scale: Float,
        stack: ItemStack,
    ) {
        pose().withMatrixContext {
            translate(x, y, 150f)
            scale(scale, scale, scale)
            renderFakeItem(stack, 0, 0)
        }
    }
}
