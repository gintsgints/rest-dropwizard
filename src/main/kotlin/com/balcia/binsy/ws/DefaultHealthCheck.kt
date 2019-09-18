package com.balcia.binsy.ws

import com.codahale.metrics.health.HealthCheck

class DefaultHealthCheck : HealthCheck() {
    override fun check(): Result {
        return Result.healthy()
    }
}
