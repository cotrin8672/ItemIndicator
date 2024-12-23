plugins {
    alias(libs.plugins.loom)
    alias(libs.plugins.architectury)
    alias(libs.plugins.shadow)
    alias(libs.plugins.kotlin)
}

kotlin.compilerOptions {
    freeCompilerArgs.add("-Xcontext-receivers")
}

architectury {
    val enabledPlatforms: String by project
    common(enabledPlatforms.split(','))
}

base {
    val modId: String by project
    val modVersion: String by project

    archivesName = modId
    version = "${modVersion}-mc${libs.versions.minecraft.get()}-${project.name}"
}

repositories {
    maven {
        url = uri("https://api.modrinth.com/maven")
        content {
            includeGroup("maven.modrinth")
        }
    }
    maven("https://maven.shedaniel.me/")
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())

    modImplementation(libs.fabric.loader)
    modApi(libs.clothConfig.fabric)
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}
