package io.github.cotrin8672.itemindicator.fabric.compat

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import io.github.cotrin8672.itemindicator.config.Config
import me.shedaniel.autoconfig.AutoConfig

class ModMenuIntegration : ModMenuApi {
    override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
        return ConfigScreenFactory { AutoConfig.getConfigScreen(Config::class.java, it).get() }
    }
}
