package org.ids.white_label.billing.currencies

import org.assertj.core.api.Assertions.assertThat
import org.ids.white_label.billing.currencies.controller.Currency
import org.ids.white_label.billing.currencies.controller.IsoCurrency
import org.ids.white_label.billing.currencies.controller.Pageable
import org.ids.white_label.billing.currencies.repository.CurrencyRepository
import org.ids.white_label.billing.currencies.utils.toDto
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.test.StepVerifier
import java.util.*

@SpringBootTest
@Testcontainers
@ContextConfiguration(initializers = [CurrenciesApplicationTests.Companion.Initializer::class])
class CurrenciesApplicationTests {

    @SpyBean
    private lateinit var repository: CurrencyRepository

    @AfterEach
    fun beforeNextTest(){
        repository.deleteCurrencies().subscribe()
    }

    @Test
    fun `currency created`() {
        val result: Mono<Currency> = requester
            .route("createCurrency")
            .data(
                Currency(
                    id = UUID.randomUUID(),
                    name = "USD",
                    isoCode = "usd11",
                    description = "some desc",
                    isActive = true
                )
            )
            .retrieveMono(Currency::class.java)

        StepVerifier
            .create(result)
            .consumeNextWith { message: Currency ->
                assertThat(message.id).isNotNull
                assertThat(message.name).isEqualTo("USD")
                assertThat(message.isoCode).isEqualTo("usd11")
                assertThat(message.description).isEqualTo("some desc")
                assertThat(message.createdAt).isNotNull
                assertThat(message.updatedAt).isNotNull
            }
            .verifyComplete()
    }

    @Test
    fun `currency added`() {
        val result: Mono<Currency> = requester
            .route("addCurrency")
            .data(
                currency()
            )
            .retrieveMono(Currency::class.java)

        StepVerifier
            .create(result)
            .consumeNextWith { message: Currency ->
                assertThat(message.id).isNotNull
                assertThat(message.name).isEqualTo("123SOS")
                assertThat(message.isoCode).isEqualTo("SOS")
                assertThat(message.description).isEqualTo("some desc of somali shilling")
                assertThat(message.createdAt).isNotNull
                assertThat(message.updatedAt).isNotNull
            }
            .verifyComplete()
    }

    @Test
    fun `currency adding not found in iso currencies`() {
        val result: Mono<Currency> = requester
            .route("addCurrency")
            .data(
                Currency(
                    id = UUID.randomUUID(),
                    name = "123SOS",
                    isoCode = "someRANDOMcode",
                    description = "some desc of somali shilling",
                    isActive = true
                )
            )
            .retrieveMono(Currency::class.java)

        StepVerifier
            .create(result)
            .consumeErrorWith {
                e -> e.message.equals("Currency was not found in dictionary!")
            }.verify()
    }

    @Test
    fun `currency updated`() {

        val currency = repository.create(currency().toRecord()).block()

        currency!!.description = "new currency description"

        val result: Mono<Currency> = requester
            .route("updateCurrency")
            .data(
                currency.toDto()
            )
            .retrieveMono(Currency::class.java)

        StepVerifier
            .create(result)
            .consumeNextWith { message: Currency ->
                assertThat(message.id).isNotNull
                assertThat(message.name).isEqualTo("123SOS")
                assertThat(message.isoCode).isEqualTo("SOS")
                assertThat(message.description).isEqualTo("new currency description")
                assertThat(message.createdAt).isNotNull
                assertThat(message.updatedAt).isNotNull
            }
            .verifyComplete()
    }

    @Test
    fun `get currency card`() {

        val currency = repository.create(currency().toRecord()).block()

        val result: Mono<Currency> = requester
            .route("getCurrencyCard")
            .data(
                currency?.id!!
            )
            .retrieveMono(Currency::class.java)

        StepVerifier
            .create(result)
            .consumeNextWith { message: Currency ->
                assertThat(message.id).isNotNull
                assertThat(message.name).isEqualTo("123SOS")
                assertThat(message.isoCode).isEqualTo("SOS")
                assertThat(message.description).isEqualTo("some desc of somali shilling")
                assertThat(message.createdAt).isNotNull
                assertThat(message.updatedAt).isNotNull
            }
            .verifyComplete()
    }

    @Test
    fun `search currencies`() {

        repository.create(currency().toRecord()).block()
        repository.create(currency2().toRecord()).block()

        val result: Flux<Currency> = requester
            .route("searchCurrencies")
            .data(
                Pageable(1, 1)
            )
            .retrieveFlux(Currency::class.java)

        StepVerifier
            .create(result)
            .consumeNextWith { message: Currency ->
                assertThat(message.id).isNotNull
                assertThat(message.name).isEqualTo("mexican money")
                assertThat(message.isoCode).isEqualTo("MXV")
                assertThat(message.description).isEqualTo("some desc of mexican money")
                assertThat(message.createdAt).isNotNull
                assertThat(message.updatedAt).isNotNull
            }
            .verifyComplete()
    }


    @Test
    fun `search all absent currencies`() {

        val result: Flux<IsoCurrency> = requester
            .route("getAbsentCurrenciesList")
            .data(Pageable(7, 1))
            .retrieveFlux(IsoCurrency::class.java)

        StepVerifier
            .create(result)
            .consumeNextWith { message: IsoCurrency ->
                assertThat(message.alphabeticCode?.isNotEmpty())
            }
            .verifyComplete()
    }


    private fun currency() = Currency(
        id = UUID.randomUUID(),
        name = "123SOS",
        isoCode = "SOS",
        description = "some desc of somali shilling",
        isActive = true
    )

    private fun currency2() = Currency(
        id = UUID.randomUUID(),
        name = "mexican money",
        isoCode = "MXV",
        description = "some desc of mexican money",
        isActive = true
    )


    companion object {
        private lateinit var requester: RSocketRequester

        @JvmStatic
        @BeforeAll
        fun setupOnce(
            @Autowired builder: RSocketRequester.Builder,
            @Value("\${spring.rsocket.server.port}") port: Int
        ) {
            @Suppress("DEPRECATION")
            requester = builder
                .connectTcp("localhost", port)
                .block()!!
        }


        @JvmStatic
        @Container
        private val postgresqlContainer: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:13.3")
            .withDatabaseName("postgres")
            .withUsername("postgres")
            .withPassword("123456")


        class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
            override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {

                TestPropertyValues.of(
                    "spring.r2dbc.url=${postgresqlContainer.jdbcUrl.replace("jdbc", "r2dbc")}",
                    "spring.r2dbc.username=${postgresqlContainer.username}",
                    "spring.r2dbc.password=${postgresqlContainer.password}",
                    "spring.liquibase.url=${postgresqlContainer.jdbcUrl}",
                    "spring.liquibase.user=${postgresqlContainer.username}",
                    "spring.liquibase.password=${postgresqlContainer.password}",
                ).applyTo(configurableApplicationContext.environment)
            }
        }
    }
}
