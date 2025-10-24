plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starters
    implementation("org.springframework.boot:spring-boot-starter")
    // Spring Cloud Gateway
    implementation("org.springframework.cloud:spring-cloud-starter-gateway:4.2.0")
}