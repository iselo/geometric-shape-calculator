plugins {
    java
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.1.7"
    jacoco
}

group = "org.test"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    setOf(
        "org.springframework.boot:spring-boot-starter-web",
        "org.springframework.boot:spring-boot-starter-validation",
        "org.springframework.boot:spring-boot-starter-webflux",
        "org.springframework.boot:spring-boot-starter-data-jpa",
        "org.hibernate:hibernate-jpamodelgen",
        "com.google.guava:guava:33.4.0-jre",
        "com.google.code.gson:gson:2.11.0"
    ).forEach { implementation(it) }

    setOf(
        "org.projectlombok:lombok",
        "org.hibernate:hibernate-jpamodelgen"
    ).forEach { annotationProcessor(it) }

    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks {
    test {
        useJUnitPlatform()
        finalizedBy(jacocoTestReport)
    }
    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required.set(true) // Codecov dependency
        }
    }
}

