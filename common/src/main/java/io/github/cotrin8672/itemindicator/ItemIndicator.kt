package io.github.cotrin8672.itemindicator

import io.github.cotrin8672.itemindicator.config.Config
import io.github.cotrin8672.itemindicator.registry.ModTags
import io.github.cotrin8672.itemindicator.render.*
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Items

object ItemIndicator {
    const val MOD_ID: String = "itemindicator"
    val CONFIG: Config by lazy { AutoConfig.getConfigHolder(Config::class.java).config }

    @JvmStatic
    fun init() {
        ItemOverlayHandler.registerOverlay(ModTags.SHULKER_BOXES, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ModTags.BEEHIVES, BeeOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ItemTags.MINING_ENCHANTABLE, EnchantmentIconOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.BEE_NEST, BeeOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.BEEHIVE, BeeOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.OMINOUS_BOTTLE, OminousBottleOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.FIREWORK_ROCKET, FireworkOverlayRenderer)

        AutoConfig.register(Config::class.java, ::GsonConfigSerializer)
    }

    fun of(name: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
}
