package org.ids.white_label.billing.currencies.controller

import io.rsocket.core.RSocketServer
import io.rsocket.core.Resume
import org.springframework.boot.rsocket.server.RSocketServerCustomizer
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

/**
 * Make the socket capable of resumption.
 * By default, the Resume Session will have a duration of 120s, a timeout of
 * 10s, and use the In Memory (volatile, non-persistent) session store.
 */

@Profile("resumption")
@Component
class RSocketServerResumptionConfig : RSocketServerCustomizer {
    override fun customize(rSocketServer: RSocketServer) {
        rSocketServer.resume(Resume())
    }
}