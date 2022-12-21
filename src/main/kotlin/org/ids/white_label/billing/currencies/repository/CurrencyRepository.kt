package org.ids.white_label.billing.currencies.repository

import org.ids.white_label.billing.currencies.generated.jooq.tables.records.CurrenciesRecord
import org.ids.white_label.billing.currencies.generated.jooq.tables.references.CURRENCIES
import org.ids.white_label.billing.currencies.generated.jooq.tables.references.ISO_CURRENCIES
import org.jooq.DSLContext
import org.jooq.impl.DSL.select
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*

@Repository
class CurrencyRepository(val dslContext: DSLContext) {

    fun deleteCurrencies() =
        Mono.from(
            dslContext.delete(CURRENCIES))

    fun getAll(page: Int, size: Int) =
        Flux.from(
            dslContext.selectFrom(CURRENCIES)
                .where(CURRENCIES.ARCHIVED_AT.isNull)
                .limit(size)
                .offset(page * size)
        )

    fun getAllAbsentIsoCurrencies(page: Int, size: Int) =
        Flux.from(
            dslContext.selectFrom(ISO_CURRENCIES)
                .where(ISO_CURRENCIES.ALPHABETIC_CODE
                    .notIn(
                        select(CURRENCIES.ISO_CODE)
                            .from(CURRENCIES)
                            .where(CURRENCIES.ARCHIVED_AT.isNull)
                    )
                )
                .limit(size)
                .offset(page * size)
        )

    fun getById(id: UUID) =
        Mono.from(
            dslContext.selectFrom(CURRENCIES)
                .where(CURRENCIES.ID.eq(id))
        )

    fun getByIsoCode(isoCode: String) =
        Mono.from(
            dslContext.selectFrom(ISO_CURRENCIES)
                .where(ISO_CURRENCIES.ALPHABETIC_CODE.eq(isoCode))
                .and(ISO_CURRENCIES.ARCHIVED_AT.isNull)
        )

    fun update(record: CurrenciesRecord) =
        Mono.from(
            dslContext.update(CURRENCIES)
                .set(record)
                .returning()
        )


    fun create(record: CurrenciesRecord) =
        Mono.from(
            dslContext.insertInto(CURRENCIES)
                .set(record)
                .returning()
        )
}