package com.balcia.binsy.ws

import com.balcia.binsy.ws.calculator.CalculatorComponent
import io.dropwizard.Application
import io.dropwizard.setup.Environment

/*
* Launch an embedded Jetty server, configured to use Jersey and Jackson
* */
fun main(args: Array<String>) {
    RestWSApp().run(*args)
}

class RestWSApp : Application<RestWSConfig>() {
    override fun run(configuration: RestWSConfig, environment: Environment) {
        println("Running ${configuration.name}!")
        val calculatorComponent = CalculatorComponent()
        environment.jersey().register(calculatorComponent)
    }
}
