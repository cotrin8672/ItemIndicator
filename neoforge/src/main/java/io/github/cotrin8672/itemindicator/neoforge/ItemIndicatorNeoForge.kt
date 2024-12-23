package io.github.cotrin8672.itemindicator.neoforge

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.neoforge.config.ConfigFactory
import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.TagsUpdatedEvent

@Mod(ItemIndicator.MOD_ID, dist = [Dist.CLIENT])
class ItemIndicatorNeoForge(container: ModContainer) {
    init {
        ItemIndicator.init()
        NeoForge.EVENT_BUS.register(this)
        container.registerExtensionPoint(
            IConfigScreenFactory::class.java,
            ConfigFactory
        )
    }

    @SubscribeEvent
    fun onTagUpdated(event: TagsUpdatedEvent) {
        if (event.updateCause == TagsUpdatedEvent.UpdateCause.CLIENT_PACKET_RECEIVED)
            ItemOverlayHandler.remapOverlay()
    }
}
