package com.ideabaker.samples.stream.kafkaspark.datastore.model

import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface MeetupRepository: ReactiveMongoRepository<MeetupRSVP, String> 