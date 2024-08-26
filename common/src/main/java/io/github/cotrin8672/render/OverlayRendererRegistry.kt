package io.github.cotrin8672.render

import net.minecraft.item.Item

object OverlayRendererRegistry {
    private val _rendererMap = mutableMapOf<Item, MutableList<OverlayRenderer>>()

    fun registerItemOverlayRenderer(item: Item, overlayRenderer: OverlayRenderer) {
        if (_rendererMap[item] == null) {
            _rendererMap[item] = mutableListOf(overlayRenderer)
        } else {
            _rendererMap[item]?.add(overlayRenderer)
        }
        _rendererMap[item]?.add(overlayRenderer) ?: run { _rendererMap[item] = mutableListOf(overlayRenderer) }
    }

    fun getItemOverlayRenderer(item: Item): List<OverlayRenderer>? {
        return _rendererMap[item]?.toList()
    }
}
