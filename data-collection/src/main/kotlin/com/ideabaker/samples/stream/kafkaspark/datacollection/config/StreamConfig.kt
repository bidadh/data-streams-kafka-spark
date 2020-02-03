package com.ideabaker.samples.stream.kafkaspark.datacollection.config

import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.context.annotation.Configuration

/**
 *
 * @author Arthur Kazemi<bidadh@gmail.com>
 * @since 4/2/20 02:47
 */

@Configuration
@EnableBinding(Bindings::class)
class StreamConfig