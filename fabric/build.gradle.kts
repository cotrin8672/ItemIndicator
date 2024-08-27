import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import com.hypherionmc.modpublisher.properties.CurseEnvironment
import com.hypherionmc.modpublisher.properties.ModLoader
import net.fabricmc.loom.task.RemapJarTask

plugins {
    alias(libs.plugins.loom)
    alias(libs.plugins.architectury)
    alias(libs.plugins.shadow)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.modPublisher)
}

architectury {
    platformSetupLoomIde()
    fabric()
}

publisher {
    val mod_version: String by project
    val mod_id: String by project

    apiKeys {
        curseforge(System.getenv("CURSE_FORGE_API_KEY"))
        modrinth(System.getenv("MODRINTH_API_KEY"))
    }

    curseID.set("1090457")
    modrinthID.set("iv1m29Zw")

    versionType.set("release")
    changelog.set(file("../changelog.md"))
    version.set(mod_version)
    displayName.set("ItemIndicator-Fabric-$mod_version")
    gameVersions.set(listOf(libs.versions.minecraft.get()))
    setLoaders(ModLoader.FABRIC)
    setCurseEnvironment(CurseEnvironment.CLIENT)
    artifact.set("build/libs/$mod_id-fabric-$mod_version.jar")

    curseDepends {
        required("architectury-api", "fabric-api", "fabric-language-kotlin")
    }

    modrinthDepends {
        required("architectury-api", "fabric-api", "fabric-language-kotlin")
    }
}

base {
    val mod_id: String by project

    archivesName = "$mod_id-fabric"
}

configurations {
    val common by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
    val compileClasspath by getting {
        extendsFrom(common)
    }

    val runtimeClasspath by getting {
        extendsFrom(common)
    }

    val developmentFabric by getting {
        extendsFrom(common)
    }

    val shadowBundle by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

@Suppress("UnstableApiUsage")
dependencies {
    minecraft(libs.minecraft)
    mappings(loom.layered {
        mappings("net.fabricmc:yarn:${libs.versions.yarnFabric.get()}")
        mappings(libs.yarn.neoforge)
    })

    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
    modImplementation(libs.fabric.kotlin)
    modImplementation(libs.architectury.fabric)

    "common"(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    "shadowBundle"(project(path = ":common", configuration = "transformProductionFabric"))
}

tasks.withType<ProcessResources>().configureEach {
    val mod_id: String by project
    val mod_name: String by project
    val mod_license: String by project
    val mod_version: String by project
    val mod_authors: String by project
    val mod_description: String by project

    val replaceProperties = mapOf(
        "minecraft_version" to libs.versions.minecraft.get(),
        "fabric_version" to libs.versions.fabricLoader.get(),
        "architectury_version" to libs.versions.architecturyApi.get(),
        "mod_id" to mod_id,
        "mod_name" to mod_name,
        "mod_license" to mod_license,
        "mod_version" to mod_version,
        "mod_authors" to mod_authors,
        "mod_description" to mod_description,
    )
    inputs.properties(replaceProperties)

    filesMatching("fabric.mod.json") {
        expand(replaceProperties)
    }

    val commonResourcesDir = project(":common").layout.projectDirectory.dir("src/main/resources")
    from(commonResourcesDir) {
        include("logo.png")
    }
    into("src/main/resources")
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.named<ShadowJar>("shadowJar") {
    configurations = listOf(project.configurations.getByName("shadowBundle"))
    archiveClassifier.set("dev-shadow")
}

tasks.named<RemapJarTask>("remapJar") {
    input.set(tasks.named<ShadowJar>("shadowJar").get().archiveFile)
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}
