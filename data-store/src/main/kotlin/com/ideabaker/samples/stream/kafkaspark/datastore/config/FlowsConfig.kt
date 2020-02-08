package com.ideabaker.samples.stream.kafkaspark.datastore.config

import com.ideabaker.samples.stream.kafkaspark.datastore.model.MeetupRSVP
import org.slf4j.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class FlowsConfig constructor(val logger: Logger) {
  @Bean
  fun read() : Consumer<MeetupRSVP> {
    return Consumer {
      logger.info("Received Meetup RSVP with id: '{}'", it.rsvpId)
    }
  }
}