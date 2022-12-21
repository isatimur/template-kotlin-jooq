package org.ids.white_label.billing.currencies.utils

import org.ids.white_label.billing.currencies.controller.Currency
import org.ids.white_label.billing.currencies.controller.IsoCurrency
import org.ids.white_label.billing.currencies.generated.jooq.tables.records.CurrenciesRecord
import org.ids.white_label.billing.currencies.generated.jooq.tables.records.IsoCurrenciesRecord

fun CurrenciesRecord.toDto(): Currency =
    Currency(
        id = id,
        name = checkNotNull(name),
        isoCode = checkNotNull(isoCode),
        description = checkNotNull(description),
        isActive = checkNotNull(isActive),
        createdAt = checkNotNull(createdAt),
        updatedAt = checkNotNull(updatedAt),
        archivedAt = archivedAt
    )

fun IsoCurrenciesRecord.toDto(): IsoCurrency =
    IsoCurrency(
        currency = checkNotNull(currency),
        alphabeticCode = checkNotNull(alphabeticCode),
        numericCode = checkNotNull(numericCode),
        minorUnit = checkNotNull(minorUnit),
        createdAt = checkNotNull(createdAt),
        updatedAt = checkNotNull(updatedAt),
        archivedAt = archivedAt
    )