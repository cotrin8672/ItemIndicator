package io.github.cotrin8672.itemindicator.forge

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.forge.config.ConfigFactory
import net.minecraftforge.client.ConfigScreenHandler
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(ItemIndicator.MOD_ID)
class ItemIndicatorForge {
    init {
        ItemIndicator.init()
        MOD_BUS.register(this)
    }

    @SubscribeEvent
    fun onFMLClientSetupEvent(event: FMLClientSetupEvent) {
        ModLoadingContext.get().registerExtensionPoint(
            ConfigScreenHandler.ConfigScreenFactory::class.java
        ) {
            ConfigScreenHandler.ConfigScreenFactory { _, screen -> ConfigFactory.createScreen(screen) }
        }
    }
}
