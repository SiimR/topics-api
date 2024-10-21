plugins {
    id("org.liquibase.gradle") version "2.2.0"
    distribution
}

apply(plugin = "org.liquibase.gradle")

dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core:4.29.2")
    liquibaseRuntime("org.postgresql:postgresql:42.7.3")
    liquibaseRuntime("org.yaml:snakeyaml:2.3")
    liquibaseRuntime("info.picocli:picocli:4.7.6")
}

liquibase {
    activities.register("main") {
        arguments = mapOf(
            "changelogFile" to "/changelog.yaml",
            "classpath" to "$projectDir/changelog",
            "url" to (project.findProperty("liquibaseDbUrl") ?: "jdbc:postgresql://localhost:5433/topics"),
            "username" to (project.findProperty("liquibaseUsername") ?: "test"),
            "password" to (project.findProperty("liquibasePassword") ?: "test"),
            "logLevel" to "info",
            "defaultSchemaName" to "topics"
        )
    }
}


distributions {
    main {
        contents {
            from(".")
            include("changelog/**")
            into("/")
        }
    }
}