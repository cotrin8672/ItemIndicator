package io.github.cotrin8672.itemindicator.util

import net.minecraft.client.gui.GuiGraphics
import net.minecraft.world.item.ItemStack

fun GuiGraphics.renderItemModel(
    x: Float,
    y: Float,
    scale: Float,
    stack: ItemStack,
) {
    pose().withMatrixContext {
        translate(x, y, 160f)
        scale(scale, scale, scale)

        renderItem(stack, 0, 0)
    }
}
