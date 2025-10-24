plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
}

repositories{
    mavenCentral()
}

dependencies{
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
}
