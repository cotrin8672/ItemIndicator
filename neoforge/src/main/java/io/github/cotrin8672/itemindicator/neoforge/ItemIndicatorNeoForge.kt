package io.github.cotrin8672.itemindicator.neoforge

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.TagsUpdatedEvent

@Mod(ItemIndicator.MOD_ID, dist = [Dist.CLIENT])
class ItemIndicatorNeoForge {
    init {
        ItemIndicator.init()
        NeoForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onTagUpdated(event: TagsUpdatedEvent) {
        ItemOverlayHandler.remapOverlay()
    }
}
