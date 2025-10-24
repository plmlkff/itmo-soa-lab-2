pluginManagement {
    plugins {
        kotlin("jvm") version "2.2.0"
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "itmo-soa-lab-2"

include(":common")
project(":common").projectDir = file("common")

include(":product")
project(":product").projectDir = file("product")

include(":ebay")
project(":ebay").projectDir = file("ebay")

include(":gateway")
project(":gateway").projectDir = file("gateway")
