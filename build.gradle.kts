plugins {
	java
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")
	compileOnly("javax.servlet:javax.servlet-api:4.0.1")
	implementation("org.glassfish.jersey.core:jersey-server:3.1.2")
	implementation("org.glassfish.jersey.containers:jersey-container-servlet:3.1.2")
	implementation("org.glassfish.jersey.media:jersey-media-json-jackson:3.1.2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
