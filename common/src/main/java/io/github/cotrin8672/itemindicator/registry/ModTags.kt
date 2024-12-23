package io.github.cotrin8672.itemindicator.registry

import io.github.cotrin8672.itemindicator.ItemIndicator
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object ModTags {
    val SHULKER_BOXES = commonBind("shulker_boxes")
    val BEEHIVES = bind("beehives")

    private fun bind(name: String): TagKey<Item> = TagKey.create(Registries.ITEM, ItemIndicator.of(name))

    private fun commonBind(name: String): TagKey<Item> =
        TagKey.create(Registries.ITEM, ResourceLocation("c", name))
}
