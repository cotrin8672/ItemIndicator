package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.Enchantments

object EnchantmentIconOverlayRenderer : ItemOverlay {
    private val FORTUNE_ICON =
        ResourceLocation(ItemIndicator.MOD_ID, "textures/gui/fortune_icon.png")
    private val SILK_TOUCH_ICON =
        ResourceLocation(ItemIndicator.MOD_ID, "textures/gui/silk_touch_icon.png")

    override fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        if (!ItemIndicator.CONFIG.enchantmentOverlayConfig.renderEnchantmentOverlay) return false

        when {
            EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SILK_TOUCH, stack) != 0 -> {
                guiGraphics.blit(SILK_TOUCH_ICON, xOffset + 1, yOffset + 1, 0f, 0f, 4, 4, 4, 4)
            }

            EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_FORTUNE, stack) != 0 -> {
                guiGraphics.blit(FORTUNE_ICON, xOffset + 1, yOffset + 1, 0f, 0f, 4, 4, 4, 4)
            }
        }
        return true
    }
}
