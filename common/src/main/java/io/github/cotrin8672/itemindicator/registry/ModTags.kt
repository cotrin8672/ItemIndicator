package io.github.cotrin8672.itemindicator.registry

import io.github.cotrin8672.itemindicator.ItemIndicator
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object ModTags {
    val SHULKER_BOXES = bind("shulker_boxes")
    val BEEHIVES = bind("beehives")
    val WAXED_ITEMS = bind("waxed_items")
    val POTION_ITEMS = bind("potion_items")
    val MINING_ENCHANTABLE = bind("mining_enchantable")

    private fun bind(name: String): TagKey<Item> = TagKey.create(Registries.ITEM, ItemIndicator.of(name))
}
