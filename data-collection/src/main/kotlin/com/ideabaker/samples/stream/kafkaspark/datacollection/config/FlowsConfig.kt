package com.ideabaker.samples.stream.kafkaspark.datacollection.config

import org.slf4j.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.dsl.Transformers
import org.springframework.integration.websocket.ClientWebSocketContainer
import org.springframework.integration.websocket.IntegrationWebSocketContainer
import org.springframework.integration.websocket.inbound.WebSocketInboundChannelAdapter
import org.springframework.messaging.MessageChannel
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.jetty.JettyWebSocketClient

@Configuration
class FlowsConfig constructor(val logger: Logger) {
  @Bean
  fun webSocketClient(): WebSocketClient {
    return JettyWebSocketClient()
  }

  @Bean
  fun clientWebSocketContainer(webSocketClient: WebSocketClient): IntegrationWebSocketContainer {
    return ClientWebSocketContainer(webSocketClient, WS_MEETUP_RSVP)
  }

  @Bean
  fun meetupRsvpWSInboundChannelAdapter(clientWebSocketContainer: IntegrationWebSocketContainer): WebSocketInboundChannelAdapter {
    val webSocketInboundChannelAdapter = WebSocketInboundChannelAdapter(clientWebSocketContainer)
    webSocketInboundChannelAdapter.outputChannel = rsvpsChannel()
    return webSocketInboundChannelAdapter
  }

  @Bean
  fun rsvpsChannel(): MessageChannel = MessageChannels.direct().get()

  @Bean
  fun handlerFlow(bindings: Bindings): IntegrationFlow {
    return IntegrationFlows.from(rsvpsChannel())
        .transform(Transformers.fromJson())
        .log<Map<String,Any>> { logger.info("Received RSVP with '{}' elements", it.payload.size) }
        .channel(bindings.rsvpsOutputChannel())
        .get()
  }

  companion object {
    private const val WS_MEETUP_RSVP = "ws://stream.meetup.com/2/rsvps"
  }
}