package org.ids.white_label.billing.currencies.config

import io.r2dbc.spi.Connection
import org.jooq.DSLContext
import org.jooq.Publisher
import org.jooq.SQLDialect
import org.jooq.conf.Settings
import org.jooq.impl.DSL
import org.springframework.r2dbc.core.DatabaseClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

/**
 * !!! Для использования реактивных транзакций с Jooq рекомендуется использовать этот класс !!!
 *
 * Этот класс создан для решения описанной ниже проблемы:
 * Rollback транзакций происходит корректно, с использованием TransactionalOperator,
 * однако реактивные методы не подхватывают соединение правильно
 * В случае с Jooq метод получает его собственное(не транзакционное R2DBC соединение),
 * так как контекст реактора не работает нормально с имплементацией Jooq.
 *
 */
class DSLAccess(private val databaseClient: DatabaseClient, private val settings: Settings) {

    private fun Connection.dsl() =
        DSL.using(this, SQLDialect.POSTGRES, settings)

    fun <T : Any> withDSLContextMany(block: (DSLContext) -> Publisher<T>): Flux<T> =
        databaseClient.inConnectionMany { con -> block(con.dsl()).toFlux() }

    fun <T : Any> withDSLContext(block: (DSLContext) -> Mono<T>): Mono<T> =
        databaseClient.inConnection { con -> block(con.dsl()) }
}