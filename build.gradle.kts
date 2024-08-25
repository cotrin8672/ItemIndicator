plugins {
    alias(libs.plugins.loom) apply false
    alias(libs.plugins.architectury)
    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.architecturyKotlin) apply false
}

architectury {
    minecraft = libs.versions.minecraft.get()
    compileOnly()
}

val mod_group_id: String by project
val mod_version: String by project

allprojects {
    group = mod_group_id
    version = mod_version
}
