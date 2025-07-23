plugins {
    id("java")
    id("fabric-loom")
}

group = "me.laxuty.project"
version = project.findProperty("mod_version") as String

val modID = project.findProperty("mod_id") as String
val modName = project.findProperty("mod_name") as String
val modDescription = project.findProperty("mod_description") as String
val modLicense = project.findProperty("mod_license") as String
val modAuthor = project.findProperty("mod_author") as String

val minecraftVersion = project.findProperty("minecraft_version") as String
val loaderVersion = project.findProperty("loader_version") as String

val fabricAPIVersion = project.findProperty("fabricAPI_version") as String
val devauthVersion = project.findProperty("devauth_version") as String
val javaAnnotationsVersion = project.findProperty("java_annotations_version") as String
val lombokVersion = project.findProperty("lombok_version") as String
val latticeVersion = project.findProperty("lattice_version") as String

base {
    archivesName.set(modName)
}

repositories {
    maven("https://maven.terraformersmc.com/")
    maven("https://pkgs.dev.azure.com/djtheredstoner/DevAuth/_packaging/public/maven/v1")
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraftVersion")
    mappings(loom.officialMojangMappings())

    modImplementation("net.fabricmc:fabric-loader:${loaderVersion}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${fabricAPIVersion}")
    modImplementation("com.moulberry:lattice:${latticeVersion}") {
        attributes {
            attribute(Attribute.of("earth.terrarium.cloche.modLoader", String::class.java), "fabric")
        }
    }

    modRuntimeOnly("me.djtheredstoner:DevAuth-fabric:${devauthVersion}")

    compileOnly("org.jetbrains:annotations:${javaAnnotationsVersion}")
    compileOnly("org.projectlombok:lombok:${lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion}")
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.base.archivesName}" }
    }
}

tasks.processResources {
    filesMatching("fabric.mod.json") {
        expand(
            mapOf(
                "mod_id" to modID,
                "mod_name" to modName,
                "mod_version" to project.version,
                "mod_description" to modDescription,
                "mod_license" to modLicense,
                "mod_author" to modAuthor,
                "minecraft_version" to minecraftVersion,
                "loader_version" to loaderVersion
            )
        )
    }
}