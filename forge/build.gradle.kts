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
    forge()
}

val modId: String by project

loom {
    forge {
        mixin {
            mixinConfig("${modId}.mixins.json")
            mixinConfig("${modId}-common.mixins.json")
            defaultRefmapName.set("${modId}.refmap.json")
        }
    }
    silentMojangMappingsLicense()
}

base {
    val modVersion: String by project

    archivesName = modId
    version = "${modVersion}+mc${libs.versions.minecraft.get()}-${project.name}"
}

publisher {
    apiKeys {
        curseforge(System.getenv("CURSE_FORGE_API_KEY"))
        modrinth(System.getenv("MODRINTH_API_KEY"))
    }

    curseID.set("1090457")
    modrinthID.set("iv1m29Zw")

    versionType.set("release")
    changelog.set(file("../changelog.md"))
    version.set(project.version.toString())
    displayName.set("ItemIndicator ${project.version}")
    gameVersions.set(listOf(libs.versions.minecraft.get()))
    setLoaders(ModLoader.FORGE)
    setCurseEnvironment(CurseEnvironment.CLIENT)
    artifact.set("build/libs/${modId}-${project.version}.jar")

    curseDepends {
        required("kotlin-for-forge")
        optional("cloth-config")
    }

    modrinthDepends {
        required("kotlin-for-forge")
        optional("cloth-config")
    }
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

    val developmentForge by getting {
        extendsFrom(common)
    }

    val shadowBundle by creating {
        isCanBeResolved = true
        isCanBeConsumed = false
    }
}

repositories {
    maven {
        name = "KotlinForForge"
        url = uri("https://thedarkcolour.github.io/KotlinForForge/")
        content { includeGroup("thedarkcolour") }
    }
    maven("https://maven.shedaniel.me/")
}

dependencies {
    minecraft(libs.minecraft)
    mappings(loom.officialMojangMappings())

    forge(libs.forge)
    implementation(libs.kotlinforforge)
    modApi(libs.clothConfig.forge)

    "common"(project(path = ":common", configuration = "namedElements")) { isTransitive = false }
    "shadowBundle"(project(path = ":common", configuration = "transformProductionFabric"))
}

tasks.withType<ProcessResources>().configureEach {
    val modId: String by project
    val modName: String by project
    val modLicense: String by project
    val modVersion: String by project
    val modAuthors: String by project
    val modDescription: String by project

    val replaceProperties = mapOf(
        "minecraftVersion" to libs.versions.minecraft.get(),
        "forgeVersion" to libs.versions.forge.get(),
        "forgeVersionRange" to libs.versions.forgeRange.get(),
        "forgeLoaderVersion" to libs.versions.kotlinforforgeRange.get(),
        "modId" to modId,
        "modName" to modName,
        "modLicense" to modLicense,
        "modVersion" to modVersion,
        "modAuthors" to modAuthors,
        "modDescription" to modDescription,
    )
    inputs.properties(replaceProperties)

    filesMatching("META-INF/mods.toml") {
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

    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

kotlin.jvmToolchain(17)

tasks.named<ShadowJar>("shadowJar") {
    configurations = listOf(project.configurations.getByName("shadowBundle"))
    archiveClassifier.set("dev-shadow")
}

tasks.named<RemapJarTask>("remapJar") {
    input.set(tasks.named<ShadowJar>("shadowJar").get().archiveFile)
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(17)
}
