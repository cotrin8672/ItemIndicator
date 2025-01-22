package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.util.withMatrixContext
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ItemStack

object FireworkOverlayRenderer : ItemOverlay {
    override fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        if (!ItemIndicator.CONFIG.renderFireworkOverlay) return false

        val fireworksDuration = stack.get(DataComponents.FIREWORKS)?.flightDuration ?: 1
        val component = if (fireworksDuration == 1) {
            Component.literal("I")
        } else {
            Component.translatable("potion.potency.${fireworksDuration - 1}")
        }
        guiGraphics.pose().withMatrixContext {
            translate(xOffset.toFloat(), yOffset.toFloat(), 160f)
            scale(0.5f, 0.5f, 0.5f)
            guiGraphics.drawCenteredString(
                textRenderer,
                component,
                6,
                3,
                0xFFFFFF
            )
        }

        return true
    }
}
