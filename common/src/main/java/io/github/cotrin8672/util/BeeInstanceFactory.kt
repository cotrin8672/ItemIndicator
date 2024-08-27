package io.github.cotrin8672.util

import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.entity.BeeEntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.entity.EntityType
import net.minecraft.entity.passive.BeeEntity

object BeeInstanceFactory {
    private lateinit var beeInstance: BeeEntity
    private lateinit var beeRendererInstance: BeeEntityRenderer

    context(MinecraftClient)
    fun getBee(): BeeEntity {
        if (!this::beeInstance.isInitialized)
            beeInstance = BeeEntity(EntityType.BEE, world!!)
        return beeInstance
    }

    context(MinecraftClient)
    fun getBeeRenderer(): BeeEntityRenderer {
        if (!this::beeRendererInstance.isInitialized)
            beeRendererInstance = BeeEntityRenderer(
                EntityRendererFactory.Context(
                    entityRenderDispatcher,
                    itemRenderer,
                    blockRenderManager,
                    gameRenderer.firstPersonRenderer,
                    resourceManager,
                    entityModelLoader,
                    textRenderer
                )
            )
        return beeRendererInstance
    }
}
