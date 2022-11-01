import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("java-library")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.2"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

group = "cc.dreamcode"
version = "1.0.0"
val mainPackage = "cc.dreamcode.antifavoring"

repositories {
    gradlePluginPortal()
    mavenCentral()
    mavenLocal()

    maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")}
    maven { url = uri("https://papermc.io/repo/repository/maven-public/")}
    maven { url = uri("https://repo.panda-lang.org/releases") }
    maven { url = uri("https://repo.eternalcode.pl/releases") }
    maven { url = uri("https://maven.enginehub.org/repo") }
}

dependencies {
    // Spigot api
    compileOnly("org.spigotmc:spigot-api:1.19-R0.1-SNAPSHOT")

    // Kyori Adventure
    implementation("net.kyori:adventure-platform-bukkit:4.1.2")
    implementation("net.kyori:adventure-text-minimessage:4.11.0")

    // Cdn
    implementation("net.dzikoysk:cdn:1.14.1")

    // bStats
    implementation("org.bstats:bstats-bukkit:3.0.0")

    // PlaceholderAPI
    compileOnly("me.clip:placeholderapi:2.11.1")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

bukkit {
    main = "$mainPackage.AntiFavoring"
    apiVersion = "1.13"
    prefix = "dreamAntiFavoring"
    author = "Piotrulla"
    name = "dreamAntiFavoring"
    version = "${project.version}"
    softDepend = listOf("PlaceholderAPI")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(8))
}

tasks.withType<JavaCompile> {
    options.compilerArgs = listOf("-Xlint:deprecation")
    options.encoding = "UTF-8"
}

tasks {
    runServer {
        minecraftVersion("1.19.1")
    }
}

tasks.withType<ShadowJar> {
    archiveFileName.set("dreamAntiFavoring v${project.version}.jar")

    exclude(
        "org/intellij/lang/annotations/**",
        "org/jetbrains/annotations/**",
        "org/checkerframework/**",
        "META-INF/**",
        "javax/**"
    )

    mergeServiceFiles()
    minimize()

    val prefix = "$mainPackage.libs"

    listOf(
        "panda",
        "org.panda_lang",
        "org.bstats",
        "net.dzikoysk",
        "net.kyori",
    ).forEach { pack ->
        relocate(pack, "$prefix.$pack")
    }
}