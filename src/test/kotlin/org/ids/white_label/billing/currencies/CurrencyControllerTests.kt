package org.ids.white_label.billing.currencies

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.ids.white_label.billing.currencies.configuration.AbstractWebFluxTestsConfiguration
import org.ids.white_label.billing.currencies.controller.Currency
import org.ids.white_label.billing.currencies.controller.CurrencyController
import org.ids.white_label.billing.currencies.controller.IsoCurrency
import org.ids.white_label.billing.currencies.service.CurrencyService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.OffsetDateTime
import java.util.*


@Import(CurrencyController::class)
@Testcontainers
class CurrencyControllerTests : AbstractWebFluxTestsConfiguration() {

    @MockBean
    lateinit var currencyService: CurrencyService

    @Test
    fun testAddCurrency() {

        val currency = currency()
        given { currencyService.create(any()) }.willReturn(Mono.just(currency))

        webTestClient.post()
            .uri("/currency/")
            .accept(MediaType.ALL)
            .body(Mono.just(currency), Currency::class.java)
            .exchange()
            .expectStatus().isOk

        verify(currencyService, times(1)).create(any())
    }

    @Test
    fun testGetCurrencyCard() {

        val currency = currency()
        given { currencyService.getById(currency.id!!) }.willReturn(Mono.just(currency))

        webTestClient.get()
            .uri("/currency/" + currency.id.toString())
            .accept(MediaType.ALL)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$['name']").isEqualTo("USD")

        verify(currencyService, times(1)).getById(currency.id!!)
    }

    @Test
    fun testUpdateCurrency() {

        val currency = currency()
        given { currencyService.create(any()) }.willReturn(Mono.just(currency))

        webTestClient.post()
            .uri("/currency/" + currency.id.toString())
            .accept(MediaType.ALL)
            .body(Mono.just(currency), Currency::class.java)
            .exchange()
            .expectStatus().isOk

        verify(currencyService, times(1)).updateCurrency(any())
    }

    @Test
    fun testGetAllCurrencies() {

        given {
            currencyService.getAllAvailableCurrencies(
                any(),
                any()
            )
        }.willReturn((Flux.fromIterable(listOf(currency(), currency()))))

        webTestClient.get()
            .uri("/currency?page=1&size=10")
            .accept(MediaType.ALL)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$[0].name").isEqualTo("USD")
            .jsonPath("$[1].name").isEqualTo("USD")

        verify(currencyService, times(1)).getAllAvailableCurrencies(1, 10)
    }

    @Test
    fun testGetAllAbsentCurrencies() {

        given { currencyService.getAllAbsentCurrencies(any(), any()) }.willReturn(
            (Flux.fromIterable(
                listOf(
                    isoCurrency(),
                    isoCurrency()
                )
            ))
        )

        webTestClient.get()
            .uri("/currency/absent-list?page=1&size=10")
            .accept(MediaType.ALL)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$[0].currency").isEqualTo("USD")
            .jsonPath("$[1].currency").isEqualTo("USD")

        verify(currencyService, times(1)).getAllAbsentCurrencies(1, 10)
    }


    private fun currency(): Currency {
        return Currency(
            id = UUID.randomUUID(),
            name = "USD",
            isoCode = "usd11",
            description = "some desc",
            isActive = true
        )
    }

    private fun isoCurrency(): IsoCurrency {
        return IsoCurrency(
            currency = "USD",
            alphabeticCode = "USD",
            numericCode = "1",
            minorUnit = 2,
            createdAt = OffsetDateTime.now(),
            updatedAt = OffsetDateTime.now(),
            archivedAt = null
        )
    }

}