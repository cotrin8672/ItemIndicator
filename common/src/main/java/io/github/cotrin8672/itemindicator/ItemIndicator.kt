package io.github.cotrin8672.itemindicator

import io.github.cotrin8672.itemindicator.registry.ModTags
import io.github.cotrin8672.itemindicator.render.BeeOverlayRenderer
import io.github.cotrin8672.itemindicator.render.EnchantmentIconOverlayRenderer
import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler
import io.github.cotrin8672.itemindicator.render.ShulkerBoxOverlayRenderer
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.ItemTags

object ItemIndicator {
    const val MOD_ID: String = "itemindicator"

    @JvmStatic
    fun init() {
        ItemOverlayHandler.registerOverlay(ModTags.SHULKER_BOXES, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ModTags.BEEHIVES, BeeOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ItemTags.MINING_ENCHANTABLE, EnchantmentIconOverlayRenderer)
    }

    fun of(name: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
}
