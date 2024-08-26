package io.github.cotrin8672.render

import io.github.cotrin8672.util.getContainerInfo
import io.github.cotrin8672.util.withMatrixContext
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.DiffuseLighting
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.item.ItemStack

object ShulkerBoxOverlayRenderer : OverlayRenderer {
    override fun render(
        guiGraphics: DrawContext,
        textRenderer: TextRenderer,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        val (displayStacks, fillLevel) = stack.getContainerInfo()
        when (displayStacks.size) {
            1 -> {
                with(MinecraftClient.getInstance()) {
                    with(guiGraphics) {
                        renderItemModel(xOffset + 5f, yOffset + 11f, 9f, displayStacks.elementAt(0))
                    }
                }
            }

            2 -> {
                with(MinecraftClient.getInstance()) {
                    with(guiGraphics) {
                        renderItemModel(xOffset + 3f, yOffset + 11f, 6.25f, displayStacks.elementAt(0))
                        renderItemModel(xOffset + 9.25f, yOffset + 11f, 6.25f, displayStacks.elementAt(1))
                    }
                }
            }

            3 -> {
                with(MinecraftClient.getInstance()) {
                    with(guiGraphics) {
                        renderItemModel(xOffset + 3f, yOffset + 12f, 6.25f, displayStacks.elementAt(0))
                        renderItemModel(xOffset + 9.25f, yOffset + 12f, 6.25f, displayStacks.elementAt(1))
                        renderItemModel(xOffset + 6.25f, yOffset + 5.75f, 6.25f, displayStacks.elementAt(2))
                    }
                }
            }
        }

        if (fillLevel > 0) {
            guiGraphics.matrices.withMatrixContext {
                val barXStart = xOffset + 13
                val barYStart = yOffset + 15
                val barXEnd = barXStart + 2
                val barYEnd = barYStart - 12
                guiGraphics.fill(RenderLayer.getGuiOverlay(), barXStart, barYStart, barXEnd, barYEnd, -16777216)
                guiGraphics.fill(
                    RenderLayer.getGuiOverlay(),
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

    context(MinecraftClient, DrawContext)
    private fun renderItemModel(
        x: Float,
        y: Float,
        scale: Float,
        stack: ItemStack,
    ) {
        val model = itemRenderer.getModel(stack, world, player, 16777216)
        matrices.withMatrixContext {
            translate(x, y, 160f)
            scale(scale, -scale, scale)
            val flag = !model.isSideLit
            if (flag) DiffuseLighting.disableGuiDepthLighting()

            itemRenderer.renderItem(
                stack,
                ModelTransformationMode.GUI,
                false,
                this,
                vertexConsumers,
                15728880,
                OverlayTexture.DEFAULT_UV,
                model
            )
            draw()
            if (flag) DiffuseLighting.enableGuiDepthLighting()
        }
    }
}
