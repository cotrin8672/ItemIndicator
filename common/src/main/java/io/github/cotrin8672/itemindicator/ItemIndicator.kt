package io.github.cotrin8672.itemindicator

import io.github.cotrin8672.itemindicator.config.Config
import io.github.cotrin8672.itemindicator.registry.ModTags
import io.github.cotrin8672.itemindicator.render.BeeOverlayRenderer
import io.github.cotrin8672.itemindicator.render.EnchantmentIconOverlayRenderer
import io.github.cotrin8672.itemindicator.render.ItemOverlayHandler
import io.github.cotrin8672.itemindicator.render.ShulkerBoxOverlayRenderer
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
        ItemOverlayHandler.registerOverlay(ModTags.BEEHIVES, BeeOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ItemTags.HOES, EnchantmentIconOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ItemTags.AXES, EnchantmentIconOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ItemTags.SHOVELS, EnchantmentIconOverlayRenderer)
        ItemOverlayHandler.registerOverlay(ItemTags.PICKAXES, EnchantmentIconOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.BEE_NEST, BeeOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.BEEHIVE, BeeOverlayRenderer)

        ItemOverlayHandler.registerOverlay(Items.SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.WHITE_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.ORANGE_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.MAGENTA_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.LIGHT_BLUE_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.YELLOW_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.LIME_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.PINK_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.GRAY_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.LIGHT_GRAY_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.CYAN_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.PURPLE_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.BLUE_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.BROWN_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.GREEN_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.RED_SHULKER_BOX, ShulkerBoxOverlayRenderer)
        ItemOverlayHandler.registerOverlay(Items.BLACK_SHULKER_BOX, ShulkerBoxOverlayRenderer)

        AutoConfig.register(Config::class.java, ::GsonConfigSerializer)
    }

    fun of(name: String): ResourceLocation = ResourceLocation(MOD_ID, name)
}
