pluginManagement {
    plugins {
        id("fabric-loom") version(settings.extra["loom_version"] as String)
    }
    repositories {
        maven("https://maven.fabricmc.net/")
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}
rootProject.name = settings.extra["mod_name"].toString()