import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.8.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
    application
}

val kotlinVersion = "1.8.0"

group = "ru.itmo.se.prog.lab6"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":common"))
    val kotlinVersion = "1.8.10"
    val koinVersion = "3.2.2"
    testImplementation(kotlin("test"))
    implementation ("io.github.microutils:kotlin-logging-jvm:2.0.11")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.8.10")
    implementation("org.reflections:reflections:0.10.2")
    implementation(kotlin("serialization", version = kotlinVersion))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0-RC")
    implementation("io.insert-koin:koin-core:$koinVersion")
    testImplementation ("io.mockk:mockk:1.13.4")
    testImplementation("io.insert-koin:koin-test-junit5:$koinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlinVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

//    implementation(project(":common"))
}

subprojects {
    apply(plugin = "org.jetbrains.dokka")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "MainKt"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}