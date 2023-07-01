plugins {
    id("java") apply true
    alias(libs.plugins.sonarqube) apply true
}

sonarqube {
    properties {
        property("sonar.projectKey", "mortenboettger_bachelorthesis-java")
        property("sonar.organization", "mboettger")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

allprojects {
    group = "io.mboettger"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    dependencies {
        implementation(platform(rootProject.libs.bom.spring.boot))
    }
}
