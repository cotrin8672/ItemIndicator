package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantments

object EnchantmentIconOverlayRenderer : ItemOverlay {
    private val FORTUNE_ICON =
        ResourceLocation.fromNamespaceAndPath(ItemIndicator.MOD_ID, "textures/gui/fortune_icon.png")
    private val SILK_TOUCH_ICON =
        ResourceLocation.fromNamespaceAndPath(ItemIndicator.MOD_ID, "textures/gui/silk_touch_icon.png")

    override fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        if (!ItemIndicator.CONFIG.renderEnchantmentOverlay) return false
        val fortune =
            Minecraft.getInstance().level?.registryAccess()?.asGetterLookup()?.get(
                Enchantments.FORTUNE.registryKey(),
                Enchantments.FORTUNE
            ) ?: return false
        val silkTouch =
            Minecraft.getInstance().level?.registryAccess()?.asGetterLookup()?.get(
                Enchantments.SILK_TOUCH.registryKey(),
                Enchantments.SILK_TOUCH
            ) ?: return false

        when {
            stack.enchantments.getLevel(silkTouch.get()) != 0 -> {
                guiGraphics.blit(SILK_TOUCH_ICON, xOffset + 1, yOffset + 1, 0f, 0f, 4, 4, 4, 4)
            }

            stack.enchantments.getLevel(fortune.get()) != 0 -> {
                guiGraphics.blit(FORTUNE_ICON, xOffset + 1, yOffset + 1, 0f, 0f, 4, 4, 4, 4)
            }
        }
        return true
    }
}
