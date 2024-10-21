pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "topics-api"
include("app")
include("domain")
include("adapter")
include("liquibase")
include("adapter:web")
findProject(":adapter:web")?.name = "web"
include("adapter:jdbc")
findProject(":adapter:jdbc")?.name = "jdbc"
