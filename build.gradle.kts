plugins {
    id("java")
}

group = "uk.co.nikodem"
version = "3.1.0"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://nexus.frengor.com/repository/public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:26.3.build.+")
    compileOnly("com.frengor:ultimateadvancementapi:3.0.0-beta-4")
    implementation("com.google.code.gson:gson:2.13.2")
    implementation("net.kyori:adventure-text-serializer-plain:4.25.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}