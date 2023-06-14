rootProject.name = "bachelorthesis-java"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

include(":core", ":domain", ":application", ":application-boundary", ":persistence-boundary", ":persistence:memory", ":web")
