dependencies {
    implementation(project(":domain"))
    implementation(project(":persistence:gateway"))
    implementation(project(":persistence:memory"))
    implementation(project(":usecase:impl"))
    implementation(project(":usecase:boundary"))
    implementation(project(":web"))

    implementation(libs.spring.boot.starter)
}
