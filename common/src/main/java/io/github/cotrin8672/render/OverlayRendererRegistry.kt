package io.github.cotrin8672.render

import net.minecraft.item.Item

object OverlayRendererRegistry {
    private val itemOverlayRendererMap = mutableMapOf<Item, MutableList<OverlayRenderer>>()
    private val _overlayRendererList = mutableListOf<OverlayRenderer>()
    val overlayRendererList
        get() = _overlayRendererList.toList()

    fun registerItemOverlayRenderer(overlayRenderer: ItemOverlayRenderer) {
        for (item in overlayRenderer.getRenderableItems()) {
            if (itemOverlayRendererMap[item] == null) {
                itemOverlayRendererMap[item] = mutableListOf(overlayRenderer)
            } else {
                itemOverlayRendererMap[item]?.add(overlayRenderer)
            }
            itemOverlayRendererMap[item]?.add(overlayRenderer) ?: run { itemOverlayRendererMap[item] = mutableListOf(overlayRenderer) }
        }
    }

    fun registerOverlayRenderer(overlayRenderer: OverlayRenderer) {
        _overlayRendererList.add(overlayRenderer)
    }

    fun getItemOverlayRenderer(item: Item): List<OverlayRenderer>? {
        return itemOverlayRendererMap[item]?.toList()
    }
}
