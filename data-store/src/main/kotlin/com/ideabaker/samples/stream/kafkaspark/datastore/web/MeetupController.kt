package com.ideabaker.samples.stream.kafkaspark.datastore.web

import com.ideabaker.samples.stream.kafkaspark.datastore.model.MeetupRSVP
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Instant

@RestController
class MeetupController(val mongoTemplate: ReactiveMongoTemplate) {
  @GetMapping("/rsvps", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
  fun rspvs(): Flux<MeetupRSVP> {
    return mongoTemplate
        .tail(Query(Criteria.where(MeetupRSVP.TIME).gte(Instant.now().nano)), MeetupRSVP::class.java)
        .share()
  }
}