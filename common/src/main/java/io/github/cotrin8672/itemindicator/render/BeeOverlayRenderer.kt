package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.util.BeeInstanceFactory
import io.github.cotrin8672.itemindicator.util.withMatrixContext
import net.minecraft.client.Minecraft
import net.minecraft.client.Timer
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.RenderType
import net.minecraft.nbt.Tag
import net.minecraft.world.item.ItemStack
import org.joml.Quaternionf

object BeeOverlayRenderer : ItemOverlay {
    val beeRenderTickCounter = Timer(3f, 0L)
    private val translate = arrayOf(
        7f to 6f,
        4f to 12f,
        10f to 12f
    )

    override fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        if (!ItemIndicator.CONFIG.renderBeeOverlay) return false
        with(guiGraphics) {
            with(Minecraft.getInstance()) {
                val tag = stack.tag ?: return false
                if (tag.contains("BlockEntityTag")) {
                    val blockEntityTag = tag.getCompound("BlockEntityTag")
                    if (blockEntityTag.contains("Bees")) {
                        val numBee = blockEntityTag.getList("Bees", Tag.TAG_COMPOUND.toInt()).size

                        for (i in 1..numBee) {
                            renderBee(xOffset + translate[i - 1].first, yOffset + translate[i - 1].second)
                        }
                    }

                    if (blockEntityTag.contains("honey_level")) {
                        val honeyLevel = blockEntityTag.getInt("honey_level")
                        renderHoneyLevel(xOffset, yOffset, honeyLevel)
                    }
                }
            }
        }
        return true
    }

    context(Minecraft)
    private fun GuiGraphics.renderBee(
        x: Float,
        y: Float,
    ) {
        pose().withMatrixContext {
            translate(x, y, 160f)
            scale(6f, -6f, 6f)
            mulPose(Quaternionf(0.0, 1.0, 0.0, Math.toRadians(-120.0)).normalize())

            val partialTicks = beeRenderTickCounter.partialTick
            BeeInstanceFactory.getBeeRenderer().render(
                BeeInstanceFactory.getBee(),
                0f,
                2 * (partialTicks - 0.5f),
                this,
                bufferSource(),
                0xF000F0
            )
        }
    }

    private fun GuiGraphics.renderHoneyLevel(
        x: Int, y: Int, level: Int,
    ) {
        if (level == 0) return

        val barXStart = x + 13
        val barYStart = y + 15
        val barXEnd = barXStart + 2
        val barYEnd = barYStart - 29

        pose().withMatrixContext {
            scale(1f, 0.5f, 1f)
            translate(0f, y.toFloat() + 15f, 160f)

            fill(RenderType.guiOverlay(), barXStart, barYStart, barXEnd, barYEnd, -16777216)
            for (i in 0 until level) {
                fill(
                    RenderType.guiOverlay(),
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
