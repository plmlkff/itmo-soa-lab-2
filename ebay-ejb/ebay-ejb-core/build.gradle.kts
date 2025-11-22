plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("jakarta.ejb:jakarta.ejb-api:4.0.1")
    compileOnly("jakarta.annotation:jakarta.annotation-api:2.1.1")
    
    implementation("org.slf4j:slf4j-api:2.0.9")
    
    implementation(project(":common")) {
        exclude(group = "org.springframework.boot")
        exclude(group = "org.springframework")
        exclude(group = "org.springframework.data")
    }
    
    implementation("org.apache.httpcomponents.client5:httpclient5:5.2.1")
    implementation("org.apache.httpcomponents.core5:httpcore5:5.2.2")
    
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.3")
    
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

