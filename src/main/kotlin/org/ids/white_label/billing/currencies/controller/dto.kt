package org.ids.white_label.billing.currencies.controller

import org.ids.white_label.billing.currencies.generated.jooq.tables.records.CurrenciesRecord
import org.ids.white_label.billing.currencies.generated.jooq.tables.records.IsoCurrenciesRecord
import java.time.OffsetDateTime
import java.util.*

data class Currency(
    val id: UUID? = null,
    val name: String,
    val isoCode: String,
    val description: String,
    val isActive: Boolean = false,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null,
    val archivedAt: OffsetDateTime? = null
) {
    fun toRecord(): CurrenciesRecord =
        CurrenciesRecord(
            id = id ?: UUID.randomUUID(),
            name = name,
            isoCode = isoCode,
            description = description,
            isActive = isActive,
            createdAt = createdAt ?: OffsetDateTime.now(),
            updatedAt = updatedAt ?: OffsetDateTime.now(),
            archivedAt = archivedAt
        )
}

data class IsoCurrency(
    val currency: String? = null,
    val alphabeticCode: String? = null,
    val numericCode: String? = null,
    val minorUnit: Int? = null,
    val createdAt: OffsetDateTime? = null,
    val updatedAt: OffsetDateTime? = null,
    val archivedAt: OffsetDateTime? = null
) {
    fun toRecord(): IsoCurrenciesRecord =
        IsoCurrenciesRecord(
            currency = currency,
            alphabeticCode = alphabeticCode,
            numericCode = numericCode,
            minorUnit = minorUnit,
            createdAt = createdAt ?: OffsetDateTime.now(),
            updatedAt = updatedAt ?: OffsetDateTime.now(),
            archivedAt = archivedAt
        )
}

data class Pageable(
    val page: Int,
    val size: Int
)