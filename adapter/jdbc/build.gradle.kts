import org.jooq.meta.jaxb.*

buildscript {
    dependencies {
        classpath("org.postgresql:postgresql:42.7.3")
        classpath("org.testcontainers:postgresql:1.20.1")
        classpath("org.jooq:jooq-codegen")
    }
}

plugins {
    id("nu.studer.jooq") version "8.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":domain"))

    implementation("org.springframework:spring-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.jooq:jooq:3.19.12")
    implementation("org.jooq:jooq-codegen:3.19.12")

    runtimeOnly("org.postgresql:postgresql:42.7.3")

    jooqGenerator("org.postgresql:postgresql:42.7.3")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

tasks.test {
    useJUnitPlatform()
}

// the jOOQ Gradle plugin configuration ↓↓↓
jooq {
    version.set("3.19.12")
    configurations {
        create("main") {  // Name of the jOOQ configuration
            generateSchemaSourceOnCompilation.set(true)

            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://localhost:5433/topics"
                    user = "test"
                    password = "test"
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        excludes = "DATABASECHANGELOG.* | PG_STAT_STATEMENTS"
                        inputSchema = "topics"
                        // Forcing jOOQ to generate Instant for timestamp with time zone mapping
                        forcedTypes.addAll(arrayOf(
                            ForcedType()
                                .withName("Instant")
                                .withIncludeTypes("timestamp\\ with\\ time\\ zone")
                        ).toList())
                    }
                    generate.apply {
                        isDeprecated = false
                        isRecords = true
                        isImmutablePojos = true
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "com.example.app"
                        directory = "${project.projectDir}/src/generated/jooq"
                    }
                }
            }
        }
    }
}