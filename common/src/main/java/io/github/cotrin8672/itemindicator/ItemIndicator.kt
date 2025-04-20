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
        ItemOverlayHandler.registerOverlay(ModTags.WAXED_ITEMS, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ModTags.POTION_ITEMS, PotionOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.OMINOUS_BOTTLE, OminousBottleOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.FIREWORK_ROCKET, FireworkOverlayRenderer)

        // Waxed Items
        ItemOverlayHandler.registerOverlay(Items.WAXED_COPPER_BLOCK, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_CHISELED_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_COPPER_GRATE, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_CUT_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_CUT_COPPER_STAIRS, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_CUT_COPPER_SLAB, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_COPPER_DOOR, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_COPPER_TRAPDOOR, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_COPPER_BULB, WaxedOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_CHISELED_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_COPPER_GRATE, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_CUT_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_CUT_COPPER_STAIRS, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_CUT_COPPER_SLAB, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_COPPER_DOOR, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_COPPER_TRAPDOOR, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_EXPOSED_COPPER_BULB, WaxedOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_CHISELED_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_COPPER_GRATE, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_CUT_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_CUT_COPPER_STAIRS, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_CUT_COPPER_SLAB, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_COPPER_DOOR, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_COPPER_TRAPDOOR, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_WEATHERED_COPPER_BULB, WaxedOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_CHISELED_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_COPPER_GRATE, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_CUT_COPPER, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_CUT_COPPER_STAIRS, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_CUT_COPPER_SLAB, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_COPPER_DOOR, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_COPPER_TRAPDOOR, WaxedOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WAXED_OXIDIZED_COPPER_BULB, WaxedOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.POTION, PotionOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.SPLASH_POTION, PotionOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.LINGERING_POTION, PotionOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.TIPPED_ARROW, PotionOverlayRenderer)

        AutoConfig.register(Config::class.java, ::GsonConfigSerializer)
    }

    fun of(name: String): ResourceLocation = ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
}
