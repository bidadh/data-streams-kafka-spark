package com.ideabaker.samples.stream.kafkaspark.datacollection

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.integration.config.EnableIntegration


@SpringBootApplication
@EnableIntegration
class DataCollectionApplication

fun main(args: Array<String>) {
	runApplication<DataCollectionApplication>(*args)
}
