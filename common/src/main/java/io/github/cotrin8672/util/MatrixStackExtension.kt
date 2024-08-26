package io.github.cotrin8672.util

import net.minecraft.client.util.math.MatrixStack

fun <T> MatrixStack.withMatrixContext(block: MatrixStack.() -> T): T {
    this.push()
    val result = this.block()
    this.pop()
    return result
}
