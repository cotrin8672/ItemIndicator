package io.github.cotrin8672.itemindicator.neoforge.config

import io.github.cotrin8672.itemindicator.config.Config
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen

object ConfigFactory {
    fun createScreen(parent: Screen): Screen {
        return AutoConfig.getConfigScreen(Config::class.java, parent).get()
    }
}
