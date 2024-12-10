package io.github.cotrin8672.itemindicator.fabric.client

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.event.lifecycle.v1.CommonLifecycleEvents

class ItemIndicatorFabricClient : ClientModInitializer {
    override fun onInitializeClient() {
        ItemIndicator.init()

        CommonLifecycleEvents.TAGS_LOADED.register { _, _ ->
            ItemOverlayHandler.remapOverlay()
        }
    }
}
