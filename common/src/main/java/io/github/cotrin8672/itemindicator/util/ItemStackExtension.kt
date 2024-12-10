package io.github.cotrin8672.itemindicator.util

import net.minecraft.core.component.DataComponents
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import java.util.stream.Collectors

val ItemStack.tagSet: Set<TagKey<Item>>
    get() = this.tags.collect(Collectors.toSet())

fun ItemStack.getContainerInfo(): Pair<Set<ItemStack>, Double> {
    if (!shulkerBoxItemInstanceList.contains(this.item)) return setOf<ItemStack>() to 0.0
    val container = components.get(DataComponents.CONTAINER) ?: return setOf<ItemStack>() to 0.0
    val fillLevel = container.nonEmptyItems().sumOf { it.count.toDouble() / it.maxStackSize.toDouble() } / 27
    val stackSet = container
        .nonEmptyItems()
        .map { it.copyWithCount(1) }
        .distinctBy { it.components.toString() + it.displayName.string }
        .toSet()
    return stackSet to fillLevel
}

val shulkerBoxItemInstanceList = listOf(
    Items.SHULKER_BOX,
    Items.WHITE_SHULKER_BOX,
    Items.ORANGE_SHULKER_BOX,
    Items.MAGENTA_SHULKER_BOX,
    Items.LIGHT_BLUE_SHULKER_BOX,
    Items.YELLOW_SHULKER_BOX,
    Items.LIME_SHULKER_BOX,
    Items.PINK_SHULKER_BOX,
    Items.GRAY_SHULKER_BOX,
    Items.LIGHT_GRAY_SHULKER_BOX,
    Items.CYAN_SHULKER_BOX,
    Items.PURPLE_SHULKER_BOX,
    Items.BLUE_SHULKER_BOX,
    Items.BROWN_SHULKER_BOX,
    Items.GREEN_SHULKER_BOX,
    Items.RED_SHULKER_BOX,
    Items.BLACK_SHULKER_BOX
)
