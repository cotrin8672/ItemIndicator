plugins {
    alias(libs.plugins.loom)
    alias(libs.plugins.architectury)
    alias(libs.plugins.shadow)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.architecturyKotlin)
}

architectury {
    val enabled_platforms: String by project
    common(enabled_platforms.split(','))
    compileOnly()
}

@Suppress("UnstableApiUsage")
dependencies {
    minecraft(libs.minecraft)
    mappings(loom.layered {
        mappings("net.fabricmc:yarn:${libs.versions.yarnFabric.get()}")
        mappings(libs.yarn.neoforge)
    })

    modImplementation(libs.fabric.loader)
    modImplementation(libs.architectury)
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}
