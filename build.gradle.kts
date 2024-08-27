plugins {
    alias(libs.plugins.loom) apply false
    alias(libs.plugins.architectury)
    alias(libs.plugins.shadow) apply false
    alias(libs.plugins.kotlin) apply false
    alias(libs.plugins.modPublisher) apply false
    java
}

val mod_id: String by project
val mod_group_id: String by project
val mod_version: String by project

architectury {
    minecraft = libs.versions.minecraft.get()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

allprojects {
    group = mod_group_id
    version = mod_version
}
