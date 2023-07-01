plugins {
    id("java") apply true
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
