package org.ids.white_label.billing.currencies

import org.ids.white_label.billing.currencies.config.DatabaseProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication(exclude = [R2dbcAutoConfiguration::class])
@EnableConfigurationProperties(DatabaseProperties::class)
class CurrenciesApplication

fun main(args: Array<String>) {
	runApplication<CurrenciesApplication>(*args)
}
