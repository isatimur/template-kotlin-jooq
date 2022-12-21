package org.ids.white_label.billing.currencies.controller

import org.ids.white_label.billing.currencies.service.CurrencyService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@RestController
@RequestMapping("/currency")
class CurrencyController(private val service: CurrencyService) {

    private val log = LoggerFactory.getLogger(javaClass)

    @PostMapping
    fun addCurrency(@RequestBody currency: Currency): Mono<Currency> {
        log.info("Received addCurrency request: $currency")
        return service.create(currency)
    }

    @GetMapping("")
    fun searchCurrencies(
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int
    ): Flux<Currency> {
        log.info("Received searchCurrencies request")
        return service.getAllAvailableCurrencies(page, size)
    }

    @GetMapping("absent-list")
    fun getAbsentCurrenciesList(
        @RequestParam(name = "page") page: Int,
        @RequestParam(name = "size") size: Int
    ): Flux<IsoCurrency> {
        log.info("Received getAbsentCurrenciesList request")
        return service.getAllAbsentCurrencies(page, size)
    }

    @PostMapping("/{id}")
    fun updateCurrency(@PathVariable("id") id: String, @RequestBody currency: Currency): Mono<Currency> {
        log.info("Received updateCurrency request: $currency")
        return service.updateCurrency(currency)
    }

    @GetMapping("{id}")
    fun getCurrencyCard(@PathVariable("id") id: String): Mono<Currency> {
        log.info("Received getCurrencyCard request: $id")
        return service.getById(UUID.fromString(id))
    }

}