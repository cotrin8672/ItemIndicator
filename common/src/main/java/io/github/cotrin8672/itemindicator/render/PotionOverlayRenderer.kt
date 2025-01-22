package io.github.cotrin8672.itemindicator.render

import io.github.cotrin8672.itemindicator.ItemIndicator
import io.github.cotrin8672.itemindicator.util.renderItemModel
import io.github.cotrin8672.itemindicator.util.withMatrixContext
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.core.component.DataComponents
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions

object PotionOverlayRenderer : ItemOverlay {
    private val glowStone = ItemStack(Items.GLOWSTONE_DUST)
    private val redStone = ItemStack(Items.REDSTONE)

    private val baseDurationMap = mapOf(
        MobEffects.FIRE_RESISTANCE to 3 * 60 * 20,
        MobEffects.REGENERATION to 45 * 20,
        MobEffects.DAMAGE_BOOST to 3 * 60 * 20,
        MobEffects.MOVEMENT_SPEED to 3 * 60 * 20,
        MobEffects.NIGHT_VISION to 3 * 60 * 20,
        MobEffects.INVISIBILITY to 3 * 60 * 20,
        MobEffects.WATER_BREATHING to 3 * 60 * 20,
        MobEffects.JUMP to 3 * 60 * 20,
        MobEffects.SLOW_FALLING to 1.5 * 60 * 20,
        MobEffects.POISON to 45 * 20,
        MobEffects.WEAKNESS to 1.5 * 60 * 20,
        MobEffects.MOVEMENT_SLOWDOWN to 1.5 * 60 * 20,
    )

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
        val potionContent = stack.get(DataComponents.POTION_CONTENTS) ?: return false
        if (potionContent.allEffects.firstOrNull() == null) return false
        if (potionContent.allEffects.toList().size > 1) return false
        val holder = potionContent.allEffects.first().effect
        val amplifier = potionContent.allEffects.first().amplifier
        val isAmplifiedDuration = longPotions.contains(potionContent.potion.get())
        val mobEffectTextureManager = Minecraft.getInstance().mobEffectTextures
        guiGraphics.pose().withMatrixContext {
            translate(0f, 0f, 160f)
            guiGraphics.blit(xOffset, yOffset, 0, 7, 7, mobEffectTextureManager.get(holder))
        }
        if (amplifier > 0)
            guiGraphics.renderItemModel(xOffset + 12f, yOffset + 12f, 6f, glowStone)
        else if (isAmplifiedDuration)
            guiGraphics.renderItemModel(xOffset + 12f, yOffset + 12f, 6f, redStone)

        return true
    }
}
