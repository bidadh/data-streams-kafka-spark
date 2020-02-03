package com.ideabaker.samples.stream.kafkaspark.datacollection.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 4/2/20 02:57
 */
@Configuration
class LoggerConfig {
  @Bean
  @Scope("prototype")
  fun logger(injectionPoint: InjectionPoint): Logger {
    return LoggerFactory.getLogger(
        injectionPoint.methodParameter?.containingClass // constructor
            ?: injectionPoint.field?.declaringClass) // or field injection
  }
}