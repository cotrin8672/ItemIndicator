package io.github.cotrin8672.itemindicator.render

import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import java.util.stream.Collectors

object ItemOverlayHandler {
    private val TAG_OVERLAY_LOOKUP = mutableMapOf<TagKey<Item>, ItemOverlay>()
    private val ITEM_OVERLAY_LOOKUP = mutableMapOf<Item, ItemOverlay>()

    fun registerOverlay(tag: TagKey<Item>, overlay: ItemOverlay) {
        val existingOverlay = TAG_OVERLAY_LOOKUP[tag]
        if (existingOverlay == null) {
            TAG_OVERLAY_LOOKUP[tag] = overlay
        } else {
            TAG_OVERLAY_LOOKUP[tag] = existingOverlay and overlay
        }
    }

    fun registerOverlay(item: Item, overlay: ItemOverlay) {
        ITEM_OVERLAY_LOOKUP[item] = overlay
    }

    @JvmStatic
    fun remapOverlay() {
        TAG_OVERLAY_LOOKUP.keys.forEach { tag ->
            BuiltInRegistries.ITEM
                .stream()
                .collect(Collectors.toSet())
                .filter { it.builtInRegistryHolder().tags().collect(Collectors.toSet()).contains(tag) }
                .forEach { item ->
                    val existingOverlay = ITEM_OVERLAY_LOOKUP[item]
                    if (existingOverlay == null) {
                        ITEM_OVERLAY_LOOKUP[item] = TAG_OVERLAY_LOOKUP[tag]!!
                    } else {
                        ITEM_OVERLAY_LOOKUP[item] = existingOverlay and TAG_OVERLAY_LOOKUP[tag]!!
                    }
                }
        }
    }

    @JvmStatic
    fun getOverlay(item: Item): ItemOverlay? {
        return ITEM_OVERLAY_LOOKUP[item]
    }
}
