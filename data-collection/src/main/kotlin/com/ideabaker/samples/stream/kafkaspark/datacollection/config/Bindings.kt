package com.ideabaker.samples.stream.kafkaspark.datacollection.config

import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel

interface Bindings {
  @Output("rsvpsOutputChannel")
  fun rsvpsOutputChannel(): MessageChannel
}
