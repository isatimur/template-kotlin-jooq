package org.ids.white_label.billing.currencies.service

import org.ids.white_label.billing.currencies.controller.Currency
import org.ids.white_label.billing.currencies.controller.IsoCurrency
import org.ids.white_label.billing.currencies.repository.CurrencyRepository
import org.ids.white_label.billing.currencies.utils.toDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Service
class CurrencyService(
    private val repository: CurrencyRepository
) {

    fun create(currency: Currency): Mono<Currency> =
        repository.create(currency.toRecord()).map { it.toDto() }

    fun add(currency: Currency): Mono<Currency> {
        return repository.getByIsoCode(currency.isoCode)
            // todo: нормально пробрасывать ошибки
            // todo: сделать валидации
            .switchIfEmpty(Mono.error(Error("Currency was not found in dictionary!")))
            .then(repository.create(currency.toRecord()))
            .map {
                it.toDto()
            }
    }

    fun getById(id: UUID): Mono<Currency> =
        repository.getById(id).map { it.toDto() }

    fun getAllAvailableCurrencies(page: Int, size: Int): Flux<Currency> =
        repository.getAll(page, size).map { it.toDto() }

    fun getAllAbsentCurrencies(page: Int, size: Int): Flux<IsoCurrency> =
        repository.getAllAbsentIsoCurrencies(page, size).map { it.toDto() }

    fun updateCurrency(currency: Currency): Mono<Currency> =
        repository.update(currency.toRecord()).map { it.toDto() }
}