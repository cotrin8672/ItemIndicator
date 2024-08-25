enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        maven { url = uri("https://maven.fabricmc.net/") }
        maven { url = uri("https://maven.architectury.dev/") }
        maven { url = uri("https://files.minecraftforge.net/maven/") }
        gradlePluginPortal()
    }
}

val mod_id: String by settings
rootProject.name = mod_id

include(":common")
include(":fabric")
include(":neoforge")
