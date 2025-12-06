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
    implementation("org.apache.cxf:cxf-spring-boot-starter-jaxws:4.0.3")

    implementation("org.springframework.retry:spring-retry")
    implementation("org.aspectj:aspectjweaver")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.3")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.apache.httpcomponents.client5:httpclient5")
    implementation("org.apache.httpcomponents.core5:httpcore5")
    implementation(project(":common"))
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation(kotlin("stdlib-jdk8"))
    
    implementation("jakarta.xml.ws:jakarta.xml.ws-api:4.0.0")
    implementation("com.sun.xml.ws:jaxws-rt:4.0.2")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
