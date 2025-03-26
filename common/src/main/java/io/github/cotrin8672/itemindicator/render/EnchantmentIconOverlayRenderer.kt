package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.RenderType
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.enchantment.Enchantments
import kotlin.jvm.optionals.getOrNull

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
            Minecraft.getInstance().level?.holderLookup(Registries.ENCHANTMENT)?.get(Enchantments.FORTUNE)?.getOrNull()
        val silkTouch =
            Minecraft.getInstance().level?.holderLookup(Registries.ENCHANTMENT)?.get(Enchantments.SILK_TOUCH)?.getOrNull()

        when {
            silkTouch?.let { stack.enchantments.getLevel(it) } != 0 -> {
                guiGraphics.blit(
                    RenderType::guiTextured,
                    SILK_TOUCH_ICON,
                    xOffset + 1, yOffset + 1, 0f, 0f, 4, 4, 4, 4
                )
            }

            fortune?.let { stack.enchantments.getLevel(it) } != 0 -> {
                guiGraphics.blit(
                    RenderType::guiTextured,
                    FORTUNE_ICON,
                    xOffset + 1, yOffset + 1, 0f, 0f, 4, 4, 4, 4
                )
            }
        }
        return true
    }
}
