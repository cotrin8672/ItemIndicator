package io.github.cotrin8672.itemindicator.config

import io.github.cotrin8672.itemindicator.ItemIndicator
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config

@Config(name = ItemIndicator.MOD_ID)
class Config : ConfigData {
    var renderShulkerBoxOverlay = true
    var renderBeeOverlay = true
    var renderEnchantmentOverlay = true
    var renderFireworkOverlay = true
    var renderOminousBottleOverlay = true
    var renderWaxedIconOverlay = true
    var renderPotionOverlay = true
}
