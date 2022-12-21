import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.6"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("nu.studer.jooq") version "8.0"
}

group = "org.ids.white_label.currencies"
version = ""
java.sourceCompatibility = JavaVersion.VERSION_11

val bootVersion = "2.7.5"
val cloudStreamVersion = "3.2.6"

repositories {
	mavenCentral()
}

extra["testcontainersVersion"] = "1.17.6"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux:${bootVersion}")
	implementation("org.springframework.boot:spring-boot-starter-actuator:${bootVersion}")
	implementation("io.micrometer:micrometer-registry-prometheus")

	implementation("org.springframework.boot:spring-boot-starter-jooq:${bootVersion}")
	implementation("org.springframework.boot:spring-boot-starter-rsocket:${bootVersion}")

	implementation("org.springframework.cloud:spring-cloud-starter-bootstrap:3.1.5")
	implementation("org.springframework.cloud:spring-cloud-starter-consul-config:3.1.2")
	implementation("org.springframework.cloud:spring-cloud-starter-consul-discovery:3.1.2")

	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.7")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")

	implementation("io.r2dbc:r2dbc-spi:1.0.0.RELEASE")
	implementation("io.r2dbc:r2dbc-pool:1.0.0.RELEASE")

	implementation("org.liquibase:liquibase-core:4.17.2")
	implementation("org.postgresql:postgresql:42.5.0")

	runtimeOnly("io.micrometer:micrometer-registry-prometheus:1.10.1")
	runtimeOnly("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")
	implementation("org.springframework.data:spring-data-r2dbc:3.0.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test:${bootVersion}")
	testImplementation("io.projectreactor:reactor-test:3.4.24")
	testImplementation("org.testcontainers:junit-jupiter:1.17.6")
	testImplementation("org.testcontainers:postgresql:1.17.6")
	testImplementation("org.springframework.cloud:spring-cloud-stream-test-support:${cloudStreamVersion}")
	implementation("org.mockito.kotlin:mockito-kotlin:4.1.0")

	jooqGenerator("org.postgresql:postgresql:42.5.0")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
	jooqGenerator("org.jooq:jooq-meta-extensions-liquibase:3.17.5")
	jooqGenerator("org.yaml:snakeyaml:1.33")

	testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

}

dependencyManagement {
	imports {
		mavenBom("org.testcontainers:testcontainers-bom:${property("testcontainersVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

jooq {
	version.set("3.17.5")
	edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)

	configurations {
		create("main") {
			generateSchemaSourceOnCompilation.set(true)

			jooqConfiguration.apply {
				logging = org.jooq.meta.jaxb.Logging.WARN

				generator.apply {
					name = "org.jooq.codegen.KotlinGenerator"

					target.apply {
						packageName = "org.ids.white_label.billing.currencies.generated.jooq"
					}

					database.apply {
						name = "org.jooq.meta.extensions.liquibase.LiquibaseDatabase"
						properties.add(
							org.jooq.meta.jaxb.Property().withKey("scripts")
								.withValue("db/changelog.yaml")
						)
						properties.add(
							org.jooq.meta.jaxb.Property().withKey("rootPath")
								.withValue("${projectDir}/src/main/resources")
						)
						properties.add(
							org.jooq.meta.jaxb.Property().withKey("includeLiquibaseTables")
								.withValue("false")
						)
					}
				}
			}
		}
	}
}

