package io.github.cotrin8672.itemindicator.neoforge.config

import io.github.cotrin8672.itemindicator.config.Config
import me.shedaniel.autoconfig.AutoConfig
import net.minecraft.client.gui.screens.Screen
import net.neoforged.fml.ModContainer
import net.neoforged.neoforge.client.gui.IConfigScreenFactory

object ConfigFactory : IConfigScreenFactory {
    override fun createScreen(modContainer: ModContainer, parent: Screen): Screen {
        return AutoConfig.getConfigScreen(Config::class.java, parent).get()
    }
}
