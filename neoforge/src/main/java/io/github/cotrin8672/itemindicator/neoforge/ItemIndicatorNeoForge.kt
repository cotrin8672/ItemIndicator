package io.github.cotrin8672.itemindicator.neoforge

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.neoforge.config.ConfigFactory
import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.Mod
import net.neoforged.neoforge.client.gui.IConfigScreenFactory

@Mod(ItemIndicator.MOD_ID, dist = [Dist.CLIENT])
class ItemIndicatorNeoForge(container: ModContainer) {
    init {
        ItemIndicator.init()
        container.registerExtensionPoint(IConfigScreenFactory::class.java, ConfigFactory)
    }
}
