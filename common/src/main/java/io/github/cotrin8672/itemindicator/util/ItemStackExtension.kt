package io.github.cotrin8672.itemindicator.util

import net.minecraft.nbt.CompoundTag
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.util.stream.Collectors

val ItemStack.tagSet: Set<TagKey<Item>>
    get() = this.tags.collect(Collectors.toSet())

fun ItemStack.getContainerInfo(): Pair<Set<ItemStack>, Double> {
    val items = this.tag?.getCompound("BlockEntityTag")?.getList("Items", 10)?.map {
        ItemStack.of(it as CompoundTag)
    } ?: return setOf<ItemStack>() to 0.0
    val fillLevel = items.filter { !it.isEmpty }.sumOf { it.count.toDouble() / it.maxStackSize.toDouble() } / 27
    val stackSet = items
        .filter { !it.isEmpty }
        .map { it.copyWithCount(1) }
        .distinctBy { it.tag.toString() + it.displayName.string }
        .toSet()
    return stackSet to fillLevel
}
