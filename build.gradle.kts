plugins {
    java
    id("org.springframework.boot") version "2.2.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("com.github.sherter.google-java-format") version "0.8"
    id("com.google.cloud.tools.jib") version "2.1.0"
    id("com.palantir.docker-run") version "0.25.0"
    id("com.palantir.git-version") version "0.12.2"
    id("com.cherryperry.gradle-file-encrypt") version "1.4.0"
}

group = "me.d4ve.iot"
version = versionDetails.gitHash

repositories {
    mavenCentral()
    maven("https://repo.spring.io/libs-release")
    maven("https://repo.spring.io/plugins-release")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    implementation("org.springframework.cloud:spring-cloud-gcp-starter:1.2.2.RELEASE")
    implementation("org.springframework.cloud:spring-cloud-gcp-starter-pubsub:1.2.2.RELEASE")
    implementation("org.springframework.integration:spring-integration-core")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

val dockerImage = "d4ve/home-env-iot-server:${project.version}"
val dockerUser: String? by project
val dockerPassword: String? by project
val deploymentSrc = "${project.projectDir}/src/deployment"
val deploymentSrcKubernetes = "$deploymentSrc/kubernetes"
val deploymentKubeConfig = "$deploymentSrcKubernetes/deployment-account/kubeconfig.secret.yaml"

java {
    sourceCompatibility = JavaVersion.VERSION_11
}

tasks.withType<Test> {
    useJUnitPlatform()
}

gradleFileEncrypt {
    files = arrayOf(
            "${project.projectDir}/src/${sourceSets.main.name}/resources/gcp_pubsub_auth.secret.json",
            "$deploymentSrcKubernetes/postgres/postgres.secret.yaml",
            "$deploymentSrcKubernetes/deployment-account/kubeconfig.secret.yaml"
    )
}

jib {
    to {
        image = dockerImage
        if (dockerUser != null) {
            auth {
                username = dockerUser
                password = dockerPassword
            }
        }
    }
    container {
        jvmFlags = listOf("-Dspring.profiles.active=production")
    }
}


dockerRun {
    name = project.name
    image = dockerImage
}

task("lint") {
    group = "verification"
    dependsOn(tasks["verifyGoogleJavaFormat"])
}

task("fixStyle") {
    group = "verification"
    dependsOn(tasks["googleJavaFormat"])
}

task("buildDocker") {
    group = "build"
    dependsOn("jibDockerBuild")
}

task("push") {
    group = "publishing"
    dependsOn("jib")
}

val writeDeploymentParameters by tasks.creating {
    group = "deployment"
    val templateFiles = fileTree(deploymentSrc) {
        include("**/*.tpl.*")
    }.files
    val parameters = mapOf(
            "DOCKER_IMAGE" to dockerImage
    )

    fun File.outputFile() = file(path.replace(".tpl", ".filled"))

    inputs.files(templateFiles)
    inputs.properties(parameters)
    outputs.files(templateFiles.map { it.outputFile() })

    doLast {
        templateFiles.forEach { templateFile ->
            var filledText = templateFile.readText()
            for ((key, value) in parameters) {
                filledText = filledText.replace("$$key", value)
            }
            templateFile.outputFile().writeText(filledText)
        }
    }
}

fun kubectlDeployTask(
        kustomization: String,
        commonTag: Pair<String, String>,
        kubeconfig: String? = null,
        block: Exec.() -> Unit = {}
) = tasks.creating(Exec::class) {
    group = "deployment"
    dependsOn(writeDeploymentParameters)

    inputs.files(fileTree(kustomization) {
        exclude("*.encrypted")
        exclude("*.tpl.*")
    })

    commandLine(
            "kubectl", "apply",
            "--kustomize", ".",
            "--prune",
            "--selector", "${commonTag.first}=${commonTag.second}",
            "--wait"
    )
    workingDir(kustomization)
    if (kubeconfig != null) {
        environment("KUBECONFIG", kubeconfig)
    }
    block()
}

val deployDeploymentAccount by kubectlDeployTask(
        kustomization = "$deploymentSrcKubernetes/deployment-account",
        commonTag = "component" to "deployment-account"
)
val deployDatabase by kubectlDeployTask(
        kustomization = "$deploymentSrcKubernetes/postgres",
        commonTag = "component" to "postgres",
        kubeconfig = deploymentKubeConfig
)
val deployServer by kubectlDeployTask(
        kustomization = "$deploymentSrcKubernetes/server",
        commonTag = "component" to "server",
        kubeconfig = deploymentKubeConfig
) {
    mustRunAfter(deployDatabase)
}

task("deploy") {
    group = "deployment"
    dependsOn(deployDatabase, deployServer)
}

val Project.versionDetails
    get() = (this.extra["versionDetails"] as groovy.lang.Closure<*>)() as com.palantir.gradle.gitversion.VersionDetails