package io.github.cotrin8672.neoforge

import io.github.cotrin8672.ItemIndicator
import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.common.Mod

@Mod(ItemIndicator.MOD_ID, dist = [Dist.CLIENT])
class ItemIndicatorNeoForge {
    init {
        ItemIndicator.init()
    }
}
