plugins {
    id("java")
    id("application")
}

group = "br.com.dio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.liquibase:liquibase-core:4.29.1")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.projectlombok:lombok:1.18.34")

    compileOnly ("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

}

application {
    mainClass.set("br.com.dio.test.TestDatabaseConnection") // ← CLASSE PRINCIPAL
}

tasks.test {
    useJUnitPlatform()
}