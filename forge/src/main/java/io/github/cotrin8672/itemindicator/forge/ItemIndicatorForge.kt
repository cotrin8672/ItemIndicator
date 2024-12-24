package io.github.cotrin8672.itemindicator.forge

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.config.Config
import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler
import me.shedaniel.autoconfig.AutoConfig
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.TagsUpdatedEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(ItemIndicator.MOD_ID)
class ItemIndicatorForge {
    init {
        ItemIndicator.init()
        MinecraftForge.EVENT_BUS.register(this)
        MOD_BUS.addListener(this::onClientSetupEvent)
    }

    @SubscribeEvent
    fun onTagUpdated(event: TagsUpdatedEvent) {
        if (event.updateCause == TagsUpdatedEvent.UpdateCause.CLIENT_PACKET_RECEIVED)
            ItemOverlayHandler.remapOverlay()
    }

    @SubscribeEvent
    fun onClientSetupEvent(event: FMLClientSetupEvent) {
        ModLoadingContext.get().registerExtensionPoint(
            ConfigScreenFactory::class.java
        ) {
            ConfigScreenFactory { parent ->
                AutoConfig.getConfigScreen(Config::class.java, parent).get()
            }
        }
    }
}
