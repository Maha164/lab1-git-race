package es.unizar.webeng.hello

import java.time.Clock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
@ConfigurationPropertiesScan
class Application {
    /**
     * Returns a clock based on the operating system's current time zone.
    */
    @Bean
    fun clock(): Clock = Clock.systemDefaultZone()
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
