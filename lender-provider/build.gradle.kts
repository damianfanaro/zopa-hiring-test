plugins {
    kotlin("jvm") version "1.3.11"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

val junitVersion = "5.3.2"

dependencies {
    implementation(kotlin("stdlib"))
    testCompile("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testCompile("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    runtime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    compile(project(":loan-calculator"))
}
