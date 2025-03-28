package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.util.withMatrixContext
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
        if (!ItemIndicator.CONFIG.renderEnchantmentOverlay) return false
        val enchantmentInstances = EnchantmentHelper.deserializeEnchantments(stack.enchantmentTags)

        if (!enchantmentInstances.contains(Enchantments.SILK_TOUCH) && !enchantmentInstances.contains(Enchantments.BLOCK_FORTUNE))
            return false

        when {
            enchantmentInstances[Enchantments.SILK_TOUCH] != 0 -> {
                guiGraphics.pose().withMatrixContext {
                    guiGraphics.blit(SILK_TOUCH_ICON, xOffset + 1, yOffset + 1, 0f, 0f, 4, 4, 4, 4)
                }
            }

            enchantmentInstances[Enchantments.BLOCK_FORTUNE] != 0 -> {
                guiGraphics.pose().withMatrixContext {
                    guiGraphics.blit(FORTUNE_ICON, xOffset + 1, yOffset + 1, 0f, 0f, 4, 4, 4, 4)
                }
            }
        }
        return true
    }
}
