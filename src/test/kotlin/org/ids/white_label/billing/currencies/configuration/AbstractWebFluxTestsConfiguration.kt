package org.ids.white_label.billing.currencies.configuration

import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest
@ExtendWith(SpringExtension::class)
@TestPropertySource(properties = ["rsocket.disabled=true"])
abstract class AbstractWebFluxTestsConfiguration {

    @Autowired
    protected lateinit var webTestClient: WebTestClient;

}
