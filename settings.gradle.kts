rootProject.name = "itmo-soa-lab-2"

include(":common")
project(":common").projectDir = file("common")

include(":product")
project(":product").projectDir = file("product")

include(":ebay")
project(":ebay").projectDir = file("ebay")

include(":gateway")
project(":gateway").projectDir = file("gateway")

include(":ebay-ejb")
project(":ebay-ejb").projectDir = file("ebay-ejb")

include(":ebay-ejb:ebay-ejb-core")
include(":ebay-ejb:ebay-ejb-web")
