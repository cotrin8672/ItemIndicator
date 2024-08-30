package io.github.cotrin8672.render

import io.github.cotrin8672.util.BeeInstanceFactory
import io.github.cotrin8672.util.withMatrixContext
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderTickCounter
import net.minecraft.component.DataComponentTypes
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.state.property.Properties
import org.joml.Quaternionf

object BeeOverlayRenderer : ItemOverlayRenderer {
    val beeRenderTickCounter = RenderTickCounter.Dynamic(3f, 0L) { value ->
        value.coerceAtLeast(MinecraftClient.getInstance().world?.tickManager?.millisPerTick ?: 0f)
    }
    private val translate = arrayOf(
        7f to 6f,
        4f to 12f,
        10f to 12f
    )

    override fun getRenderableItems(): List<Item> {
        return listOf(Items.BEE_NEST, Items.BEEHIVE)
    }

    override fun render(
        guiGraphics: DrawContext,
        textRenderer: TextRenderer,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        with(guiGraphics) {
            with(MinecraftClient.getInstance()) {
                val numBee = stack.components.get(DataComponentTypes.BEES)?.size ?: 0
                for (i in 1 .. numBee) {
                    renderBee(xOffset + translate[i - 1].first, yOffset + translate[i - 1].second)
                }
            }
            val honeyLevel = stack.components.get(DataComponentTypes.BLOCK_STATE)?.getValue(Properties.HONEY_LEVEL)
            renderHoneyLevel(xOffset, yOffset, honeyLevel ?: 0)
        }
        return true
    }

    context(MinecraftClient, DrawContext)
    private fun renderBee(
        x: Float,
        y: Float,
    ) {
        matrices.withMatrixContext {
            translate(x, y, 160f)
            scale(6f, -6f, 6f)
            multiply(Quaternionf(0.0, 1.0, 0.0, Math.toRadians(-120.0)).normalize())

            val partialTicks = beeRenderTickCounter.getTickDelta(true)
            BeeInstanceFactory.getBeeRenderer().render(
                BeeInstanceFactory.getBee(),
                0f,
                if (partialTicks * 2 >= 1) 2 - partialTicks * 2 else partialTicks * 2,
                this,
                vertexConsumers,
                0xF000F0
            )
        }
    }

    context(DrawContext)
    private fun renderHoneyLevel(
        x: Int, y: Int, level: Int
    ) {
        if (level == 0) return

        val barXStart = x + 13
        val barYStart = y + 15
        val barXEnd = barXStart + 2
        val barYEnd = barYStart - 29

        matrices.withMatrixContext {
            scale(1f, 0.5f, 1f)
            translate(0f, y.toFloat() + 15f, 160f)

            fill(RenderLayer.getGuiOverlay(), barXStart, barYStart, barXEnd, barYEnd, -16777216)
            for (i in 0 until level) {
                fill(
                    RenderLayer.getGuiOverlay(),
                    barXStart,
                    barYStart - 6 * i,
                    barXEnd - 1,
                    barYStart - 6 * i - 5,
                    (0xFFFF941DL and 0xFFFFFFFFL).toInt()
                )
            }
        }
    }
}
