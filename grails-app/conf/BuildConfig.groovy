grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

grails.plugin.location.'at-smart-storage' = '../AtSmartStorage'

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    legacyResolve false // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()
		
		mavenRepo "http://repo.spring.io/milestone/"
		mavenRepo "https://repository.apache.org/content/repositories/snapshots/"
		mavenRepo "http://repository.codehaus.org"
		mavenRepo "http://repo.spring.io/milestone/"
		mavenRepo "http://maven.springframework.org/release/"
    }
    dependencies {
		compile ("org.apache.jena:jena-core:2.12.0") 
		compile ("org.apache.jena:jena-arq:2.12.0")
    }

    plugins {
        build(":tomcat:$grailsVersion",
              ":release:2.2.0",
              ":rest-client-builder:1.0.3") {
            export = false
        }
			  
		runtime ":hibernate:$grailsVersion"
    }
}
