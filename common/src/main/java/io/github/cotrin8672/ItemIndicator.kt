package io.github.cotrin8672

import io.github.cotrin8672.render.BeeOverlayRenderer
import io.github.cotrin8672.render.OverlayRendererRegistry
import io.github.cotrin8672.render.ShulkerBoxOverlayRenderer
import io.github.cotrin8672.util.shulkerBoxItemInstanceList

object ItemIndicator {
    const val MOD_ID: String = "itemindicator"

    @JvmStatic
    fun init() {
        for (shulkerbox in shulkerBoxItemInstanceList) {
            OverlayRendererRegistry.registerItemOverlayRenderer(ShulkerBoxOverlayRenderer)
        }
        OverlayRendererRegistry.registerItemOverlayRenderer(BeeOverlayRenderer)
        OverlayRendererRegistry.registerItemOverlayRenderer(BeeOverlayRenderer)
    }
}
