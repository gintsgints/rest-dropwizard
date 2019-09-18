package com.balcia.binsy.ws

import com.balcia.binsy.ws.resources.calculator.CalculatorComponent
import io.dropwizard.Application
import io.dropwizard.setup.Environment

/*
* Launch an embedded Jetty server, configured to use Jersey and Jackson
* */
fun main(args: Array<String>) {
    RestWSApp().run(*args)
}

class RestWSApp : Application<RestWSConfig>() {
    override fun run(configuration: RestWSConfig, env: Environment) {
        println("Running ${configuration.appName}!")
        env.jersey().register(RootResource(configuration.appName))
        env.healthChecks().register("default", DefaultHealthCheck())
        val calculatorComponent = CalculatorComponent()
        env.jersey().register(calculatorComponent)
    }
}
