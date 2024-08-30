package io.github.cotrin8672.render

import io.github.cotrin8672.ItemIndicator
import net.minecraft.client.MinecraftClient
import net.minecraft.client.font.TextRenderer
import net.minecraft.client.gui.DrawContext
import net.minecraft.component.DataComponentTypes
import net.minecraft.enchantment.Enchantments
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object EnchantmentIconOverlayRenderer : OverlayRenderer  {
    private val FORTUNE_ICON = Identifier.of(ItemIndicator.MOD_ID, "textures/gui/fortune_icon.png")
    private val SILK_TOUCH_ICON = Identifier.of(ItemIndicator.MOD_ID, "textures/gui/silk_touch_icon.png")

    override fun shouldRender(stack: ItemStack): Boolean {
        val enchantments = stack.components.get(DataComponentTypes.ENCHANTMENTS) ?: return false
        val fortune =
            MinecraftClient.getInstance().world?.registryManager?.createRegistryLookup()?.getOptionalEntry(
                Enchantments.FORTUNE.registryRef,
                Enchantments.FORTUNE
            ) ?: return false
        val silkTouch =
            MinecraftClient.getInstance().world?.registryManager?.createRegistryLookup()?.getOptionalEntry(
                Enchantments.SILK_TOUCH.registryRef,
                Enchantments.SILK_TOUCH
            ) ?: return false
        return enchantments.getLevel(fortune.get()) != 0 || enchantments.getLevel(silkTouch.get()) != 0
    }

    override fun render(
        guiGraphics: DrawContext,
        textRenderer: TextRenderer,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        val fortune =
            MinecraftClient.getInstance().world?.registryManager?.createRegistryLookup()?.getOptionalEntry(
                Enchantments.FORTUNE.registryRef,
                Enchantments.FORTUNE
            ) ?: return false
        val silkTouch =
            MinecraftClient.getInstance().world?.registryManager?.createRegistryLookup()?.getOptionalEntry(
                Enchantments.SILK_TOUCH.registryRef,
                Enchantments.SILK_TOUCH
            ) ?: return false

        when {
            stack.enchantments.getLevel(silkTouch.get()) != 0 -> {
                guiGraphics.drawTexture(SILK_TOUCH_ICON, xOffset, yOffset, 0f, 0f, 7, 7, 7, 7)
            }
            stack.enchantments.getLevel(fortune.get()) != 0 -> {
                guiGraphics.drawTexture(FORTUNE_ICON, xOffset, yOffset, 0f, 0f, 7, 7, 7, 7)
            }
        }
        return true
    }
}
