package com.ideabaker.samples.stream.kafkaspark.datastore.config

import com.ideabaker.samples.stream.kafkaspark.datastore.model.MeetupRSVP
import com.ideabaker.samples.stream.kafkaspark.datastore.model.MeetupRepository
import org.slf4j.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Consumer

@Configuration
class FlowsConfig constructor(val meetupRepository: MeetupRepository, val logger: Logger) {
  @Bean
  fun read() : Consumer<MeetupRSVP> {
    return Consumer {
      logger.info("Saving Meetup RSVP with id: '{}'", it.rsvpId)
      meetupRepository.save(it)
          .subscribe { rsvp ->
            logger.info("Saved RSVP. id: '{}'", rsvp.id!!)
          }
    }
  }
}