package com.ideabaker.samples.stream.kafkaspark.datacollection

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.integration.channel.NullChannel
import org.springframework.integration.config.EnableIntegration
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.MessageChannels
import org.springframework.integration.dsl.Transformers
import org.springframework.integration.websocket.ClientWebSocketContainer
import org.springframework.integration.websocket.IntegrationWebSocketContainer
import org.springframework.integration.websocket.inbound.WebSocketInboundChannelAdapter
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHeaders
import org.springframework.web.socket.client.WebSocketClient
import org.springframework.web.socket.client.jetty.JettyWebSocketClient


@SpringBootApplication
@EnableIntegration
class DataCollectionApplication {
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
		webSocketInboundChannelAdapter.outputChannel = logChannel()
		return webSocketInboundChannelAdapter
	}

	@Bean
	fun logChannel(): MessageChannel = MessageChannels.direct().get()

	@Bean
	fun handlerFlow(): IntegrationFlow {
		return IntegrationFlows.from(logChannel())
				.transform(Transformers.fromJson())
				.handle { payload: Any, headers: MessageHeaders? ->
					println(payload)
					println(headers)
					println("--------")
				}
				.channel(NullChannel())
				.get()
	}

	companion object {
		private const val WS_MEETUP_RSVP = "ws://stream.meetup.com/2/rsvps"
	}
}

fun main(args: Array<String>) {
	runApplication<DataCollectionApplication>(*args)
}
