package com.balcia.binsy.ws

import com.balcia.binsy.ws.resources.calculator.CalculatorComponent
import com.balcia.binsy.ws.resources.root.RootResource
import io.dropwizard.Application
import io.dropwizard.setup.Environment


class RestWSApp : Application<RestWSConfig>() {

    override fun run(configuration: RestWSConfig, env: Environment) {
        println("Running ${configuration.appName}!")
        env.jersey().register(RootResource(configuration.appName))
        env.healthChecks().register("default", DefaultHealthCheck())
        val calculatorComponent = CalculatorComponent()
        env.jersey().register(calculatorComponent)
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            RestWSApp().run(*args)
        }
    }
}
