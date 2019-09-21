package com.balcia.binsy.ws

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.constraints.NotNull


class RestWSConfig(
    @JsonProperty("database")
    @NotNull
    val dataSourceFactory: DataSourceFactory
) : Configuration() {
    @JsonProperty
    @NotEmpty
    val appName: String = "Example rest Appcliation"
}
