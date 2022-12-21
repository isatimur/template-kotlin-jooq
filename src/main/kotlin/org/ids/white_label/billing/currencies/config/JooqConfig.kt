package org.ids.white_label.billing.currencies.config

import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.conf.RenderNameCase
import org.jooq.conf.Settings
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.r2dbc.connection.TransactionAwareConnectionFactoryProxy
import org.springframework.r2dbc.core.DatabaseClient


@ConfigurationProperties(prefix = "spring.r2dbc")
@ConstructorBinding
data class DatabaseProperties(
    val url: String?,
    val username: String?,
    val password: String?,
)

@Configuration
class JooqConfig(
    val properties: DatabaseProperties
) {

    @Bean
    fun connectionFactory(): ConnectionFactory =
        ConnectionFactories.get(
            ConnectionFactoryOptions
                .parse(properties.url!!)
                .mutate()
                .option(ConnectionFactoryOptions.USER, properties.username!!)
                .option(ConnectionFactoryOptions.PASSWORD, properties.password!!)
                .build()
        )

    @Bean
    fun context(connectionFactory: ConnectionFactory): DSLContext =
        DSL.using(
            DefaultConfiguration()
                .set(TransactionAwareConnectionFactoryProxy(connectionFactory))
                .set(SQLDialect.POSTGRES)
                .set(settings())
        ).dsl()

    @Bean
    fun dslAccess(connectionFactory: ConnectionFactory) :
            DSLAccess = DSLAccess(DatabaseClient.create(connectionFactory), settings())

    private fun settings() =
        Settings()
            .withRenderNameCase(RenderNameCase.LOWER)
            .withBindOffsetDateTimeType(true)
            .withBindOffsetTimeType(true)
}