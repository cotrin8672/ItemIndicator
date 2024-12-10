package io.github.cotrin8672.itemindicator.render

import com.mojang.blaze3d.platform.Lighting
import io.github.cotrin8672.itemindicator.util.getContainerInfo
import io.github.cotrin8672.itemindicator.util.withMatrixContext
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

object ShulkerBoxOverlayRenderer : ItemOverlay {
    override fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        val (displayStacks, fillLevel) = stack.getContainerInfo()
        when (displayStacks.size) {
            1 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 5f, yOffset + 11f, 9f, displayStacks.elementAt(0))
                }
            }

            2 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 3f, yOffset + 11f, 6.25f, displayStacks.elementAt(0))
                    renderItemModel(xOffset + 9.25f, yOffset + 11f, 6.25f, displayStacks.elementAt(1))
                }
            }

            3 -> {
                with(guiGraphics) {
                    renderItemModel(xOffset + 3f, yOffset + 12f, 6.25f, displayStacks.elementAt(0))
                    renderItemModel(xOffset + 9.25f, yOffset + 12f, 6.25f, displayStacks.elementAt(1))
                    renderItemModel(xOffset + 6.25f, yOffset + 5.75f, 6.25f, displayStacks.elementAt(2))
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
}
