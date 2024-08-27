package io.github.cotrin8672.render

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

interface ItemOverlayRenderer : OverlayRenderer {
    override fun shouldRender(stack: ItemStack): Boolean {
        return getRenderableItems().contains(stack.item)
    }

    fun getRenderableItems(): List<Item>
}
