package com.balcia.binsy.ws

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

class RestWSConfig() : Configuration() {
    @JsonProperty
    @NotEmpty
    val appName: String = "appName"
}
