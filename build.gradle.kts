plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("com.sparkjava:spark-core:2.9.4")
    implementation ("com.sparkjava:spark-template-handlebars:2.7.1")
    implementation ("org.postgresql:postgresql:42.6.0")
    implementation ("org.sql2o:sql2o:1.6.0")
}

tasks.test {
    useJUnitPlatform()
}
