package io.github.cotrin8672.itemindicator.util

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import java.util.stream.Collectors

val ItemStack.tagSet: Set<TagKey<Item>>
    get() = this.tags.collect(Collectors.toSet())

fun ItemStack.getContainerInfo(): Pair<Set<ItemStack>, Double> {
    if (!shulkerBoxItemInstanceList.contains(this.item)) {
        return emptySet<ItemStack>() to 0.0
    }

    val tag = this.tag ?: return emptySet<ItemStack>() to 0.0
    val blockEntityTag = tag.getCompound("BlockEntityTag")
    if (!blockEntityTag.contains("Items", 9)) { // 9 = NbtElement.LIST_TYPE (Yarnの場合)
        return emptySet<ItemStack>() to 0.0
    }

    val itemsList = blockEntityTag.getList("Items", 10) // 10 = NbtElement.COMPOUND_TYPE
    if (itemsList.isEmpty()) {
        return emptySet<ItemStack>() to 0.0
    }
    val itemStacks = mutableListOf<ItemStack>()
    for (i in 0 until itemsList.size) {
        val itemCompound = itemsList.getCompound(i)
        val insideStack = ItemStack.of(itemCompound)
        itemStacks.add(insideStack)
    }

    val fillLevel = itemStacks
        .sumOf { it.count.toDouble() / it.maxStackSize } / 27.0

    val distinctStacks = itemStacks
        .map { it.copyWithCount(1) }
        .distinctBy { (it.tag?.toString() ?: "") + it.displayName.string }
        .toSet()

    return distinctStacks to fillLevel
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
