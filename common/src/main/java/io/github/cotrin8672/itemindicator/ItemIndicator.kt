package io.github.cotrin8672.itemindicator

import io.github.cotrin8672.itemindicator.config.Config
import io.github.cotrin8672.itemindicator.registry.ModTags
import io.github.cotrin8672.itemindicator.render.*
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.Items

object ItemIndicator {
    const val MOD_ID: String = "itemindicator"
    val CONFIG: Config by lazy { AutoConfig.getConfigHolder(Config::class.java).config }

    @JvmStatic
    fun init() {
        ItemOverlayHandler.registerOverlay(ModTags.SHULKER_BOXES, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ModTags.BEEHIVES, BeeOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ModTags.MINING_ENCHANTABLE, EnchantmentIconOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ModTags.WAXED_ITEMS, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ModTags.POTION_ITEMS, PotionOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.FIREWORK_ROCKET, FireworkOverlayRenderer)

        AutoConfig.register(Config::class.java, ::GsonConfigSerializer)
    }

    fun of(name: String): ResourceLocation = ResourceLocation(MOD_ID, name)
}
