package io.github.cotrin8672.itemindicator.fabric.client

import io.github.cotrin8672.itemindicator.ItemIndicator
import net.fabricmc.api.ClientModInitializer

class ItemIndicatorFabricClient : ClientModInitializer {
    override fun onInitializeClient() {
        ItemIndicator.init()
    }
}
