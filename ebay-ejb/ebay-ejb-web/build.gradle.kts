plugins {
    java
    war
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":ebay-ejb:ebay-ejb-core"))
    
    implementation(project(":common")) {
        exclude(group = "org.springframework.boot")
        exclude(group = "org.springframework")
        exclude(group = "org.springframework.data")
    }
    
    providedCompile("jakarta.platform:jakarta.jakartaee-api:10.0.0")
    providedCompile("jakarta.ejb:jakarta.ejb-api:4.0.1")
    
    providedCompile("jakarta.ws.rs:jakarta.ws.rs-api:3.1.0")
    providedCompile("jakarta.servlet:jakarta.servlet-api:6.0.0")
    
    providedCompile("jakarta.json.bind:jakarta.json.bind-api:3.0.0")
    
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

