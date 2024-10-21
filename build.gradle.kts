repositories {
  mavenCentral()
}

plugins {
  id("java")
  id("org.springframework.boot") version "3.3.2"
  id("io.spring.dependency-management") version "1.1.6"
  id("io.freefair.lombok") version "8.7.1"
}

allprojects {

  repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
  }

  apply(plugin = "java-library")
  apply(plugin = "io.freefair.lombok")
  apply(plugin = "org.springframework.boot")
  apply(plugin = "io.spring.dependency-management")

  java {
    toolchain {
      languageVersion.set(JavaLanguageVersion.of(21))
    }
  }

  dependencies {
    implementation("org.mapstruct:mapstruct:1.6.0")
    implementation("org.springframework:spring-context")

    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.0")
  }

  tasks.test {
    useJUnitPlatform()
  }
}

subprojects {
  tasks.jar {
    enabled = true
  }

  tasks.bootJar {
    enabled = false
  }

  tasks.bootRun {
    enabled = false
  }

  tasks.bootBuildImage {
    enabled = false
  }
}

dependencies {
  implementation(project(":app"))
}

springBoot {
  mainClass.set("com.example.app.App")
}
