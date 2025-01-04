// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("org.openapi.generator") version "7.10.0"
}

val generatedSourcesPath = "$buildDir/app/generated"
val apiDescriptionFile = "$rootDir/app/src/main/openapi.json"
val apiRootName = "pt.ipca.doamais.api"

openApiGenerate {
    generatorName.set("kotlin")
    inputSpec.set(apiDescriptionFile)
    outputDir.set(generatedSourcesPath)
    apiPackage.set("$apiRootName.api")
    invokerPackage.set("$apiRootName.invoker")
    modelPackage.set("$apiRootName.model")
}