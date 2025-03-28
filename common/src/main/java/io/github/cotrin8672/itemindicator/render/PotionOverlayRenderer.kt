package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.util.renderItemModel
import io.github.cotrin8672.itemindicator.util.withMatrixContext
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.PotionUtils
import net.minecraft.world.item.alchemy.Potions

object PotionOverlayRenderer : ItemOverlay {
    private val glowStone = ItemStack(Items.GLOWSTONE_DUST)
    private val redStone = ItemStack(Items.REDSTONE)
    private val turtleScute = ItemStack(Items.SCUTE)

    private val longPotions = mutableSetOf(
        Potions.LONG_POISON,
        Potions.LONG_SWIFTNESS,
        Potions.LONG_STRENGTH,
        Potions.LONG_REGENERATION,
        Potions.LONG_FIRE_RESISTANCE,
        Potions.LONG_WATER_BREATHING,
        Potions.LONG_LEAPING,
        Potions.LONG_SLOWNESS,
        Potions.LONG_TURTLE_MASTER,
        Potions.LONG_NIGHT_VISION,
        Potions.LONG_INVISIBILITY,
        Potions.LONG_SLOW_FALLING,
        Potions.LONG_WEAKNESS,
    )

    override fun render(
        guiGraphics: GuiGraphics,
        textRenderer: Font,
        stack: ItemStack,
        xOffset: Int,
        yOffset: Int,
    ): Boolean {
        if (!ItemIndicator.CONFIG.renderPotionOverlay) return false
        val effects = PotionUtils.getAllEffects(stack.tag)
        if (effects.isEmpty()) return false

        val potion = PotionUtils.getPotion(stack)
        val isTurtleMasterPotion = potion != Potions.TURTLE_MASTER ||
                potion != Potions.STRONG_TURTLE_MASTER || potion != Potions.LONG_TURTLE_MASTER
        if (effects.size > 1 && !isTurtleMasterPotion) return false

        val effect = PotionUtils.getAllEffects(stack.tag).first().effect
        val amplifier = PotionUtils.getAllEffects(stack.tag).first().amplifier
        val isAmplifiedDuration = longPotions.contains(potion)
        val mobEffectTextureManager = Minecraft.getInstance().mobEffectTextures

        when (potion) {
            Potions.TURTLE_MASTER,
            Potions.LONG_TURTLE_MASTER,
            Potions.STRONG_TURTLE_MASTER,
                -> guiGraphics.renderItemModel(xOffset + 3f, yOffset + 3f, 7f, turtleScute)

            else
                -> guiGraphics.pose().withMatrixContext {
                translate(0f, 0f, 160f)
                guiGraphics.blit(xOffset, yOffset, 0, 7, 7, mobEffectTextureManager.get(effect))
            }
        }

        if (amplifier > 0)
            guiGraphics.renderItemModel(xOffset + 12f, yOffset + 12f, 6f, glowStone)
        else if (isAmplifiedDuration)
            guiGraphics.renderItemModel(xOffset + 12f, yOffset + 12f, 6f, redStone)

        return true
    }
}
