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
    implementation("org.springframework.boot:spring-boot-starter-jetty")
    implementation("org.springframework.retry:spring-retry")
    implementation("org.aspectj:aspectjweaver")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.4.3")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.13")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.apache.httpcomponents.client5:httpclient5")
    implementation("org.apache.httpcomponents.core5:httpcore5")
    implementation(project(":common"))
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation(kotlin("stdlib-jdk8"))
    
    implementation("org.apache.cxf:cxf-spring-boot-starter-jaxws:4.0.3")
    implementation("jakarta.xml.ws:jakarta.xml.ws-api:4.0.0")
    implementation("com.sun.xml.ws:jaxws-rt:4.0.2")
}

configurations{
    all {
        exclude(group="org.springframework.boot", module="spring-boot-starter-tomcat")
    }
}


tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
