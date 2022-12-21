package org.ids.white_label.billing.currencies.controller

import org.ids.white_label.billing.currencies.service.CurrencyService
import org.slf4j.LoggerFactory
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.UUID

@Controller
class RSocketController(
    private val service: CurrencyService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @MessageMapping("createCurrency")
    fun createCurrency(currency: Currency): Mono<Currency> {
        log.info("Received createCurrency request: $currency")
        return service.create(currency)
    }

    @MessageMapping("addCurrency")
    fun addCurrency(@RequestBody currency: Currency): Mono<Currency> {
        log.info("Received createCurrency request: $currency")
        return service.add(currency)
    }

    @MessageMapping("searchCurrencies")
    fun searchCurrencies(pageable: Pageable): Flux<Currency> {
        log.info("Received searchCurrencies request")
        return service.getAllAvailableCurrencies(pageable.page, pageable.size)
    }

    @MessageMapping("getAbsentCurrenciesList")
    fun getAbsentCurrenciesList(pageable: Pageable): Flux<IsoCurrency> {
        log.info("Received getAbsentCurrenciesList request")
        return service.getAllAbsentCurrencies(pageable.page, pageable.size)
    }

    @MessageMapping("getCurrencyCard")
    fun getCurrencyCard(id: UUID): Mono<Currency> {
        log.info("Received getCurrencyCard request: $id")
        return service.getById(id)
    }

    @MessageMapping("updateCurrency")
    fun updateCurrency(currency: Currency): Mono<Currency> {
        log.info("Received updateCurrency request: $currency")
        return service.updateCurrency(currency)
    }
}