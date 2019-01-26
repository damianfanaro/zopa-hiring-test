plugins {
    kotlin("jvm") version "1.3.11"
}

val junitVersion = "5.3.2"

dependencies {
    implementation(kotlin("stdlib"))
    testCompile("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testCompile("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    runtime("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    compile(project(":loan-calculator"))
}
