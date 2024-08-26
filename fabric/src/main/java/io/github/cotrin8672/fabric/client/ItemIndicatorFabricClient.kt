package io.github.cotrin8672.fabric.client

import io.github.cotrin8672.ItemIndicator
import net.fabricmc.api.ClientModInitializer

class ItemIndicatorFabricClient : ClientModInitializer {
    override fun onInitializeClient() {
        ItemIndicator.init()
    }
}
