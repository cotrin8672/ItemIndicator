package io.github.cotrin8672.itemindicator.util

import com.mojang.blaze3d.vertex.PoseStack

fun <T> PoseStack.withMatrixContext(block: PoseStack.() -> T): T {
    this.pushPose()
    val result = this.block()
    this.popPose()
    return result
}
