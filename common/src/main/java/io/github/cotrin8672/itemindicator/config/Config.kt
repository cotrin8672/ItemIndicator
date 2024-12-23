package io.github.cotrin8672.itemindicator.config

import io.github.cotrin8672.itemindicator.ItemIndicator
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry

@Config(name = ItemIndicator.MOD_ID)
object Config : ConfigData {
    @ConfigEntry.Gui.CollapsibleObject
    val shulkerOverlayConfig = ShulkerOverlayConfig

    @ConfigEntry.Gui.CollapsibleObject
    val beeOverlayConfig = BeeOverlayConfig

    @ConfigEntry.Gui.CollapsibleObject
    val enchantmentOverlayConfig = EnchantmentOverlayConfig

    object ShulkerOverlayConfig {
        var renderShulkerOverlay = true
    }

    object BeeOverlayConfig {
        var renderBeeOverlay = true
    }

    object EnchantmentOverlayConfig {
        var renderEnchantmentOverlay = true
    }
}
