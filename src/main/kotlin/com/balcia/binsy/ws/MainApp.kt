package com.balcia.binsy.ws

import io.dropwizard.Application
import io.dropwizard.Configuration
import io.dropwizard.setup.Environment
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.WebApplicationException

/*
* Launch an embedded Jetty server, configured to use Jersey and Jackson
* */
fun main(args: Array<String>) {
    CalculatorApp().run(*args)
}

class CalculatorConfig(val name: String = "unknown") : Configuration()

@Path("/")
class CalculatorComponent {
    @Path("/add")
    @GET
    fun add(@QueryParam("a") a: Double, @QueryParam("b") b: Double): Double {
        return a + b
    }

    @Path("/multiply")
    @GET
    fun multiply(@QueryParam("a") a: Double, @QueryParam("b") b: Double): Double {
        return a * b
    }

    @Path("/divide")
    @GET
    fun divide(@QueryParam("a") a: Double, @QueryParam("b") b: Double): Double {
        if (b == .0) {
            throw WebApplicationException("Can't divide by zero")
        }
        return a / b
    }
}

class CalculatorApp : Application<CalculatorConfig>() {
    override fun run(configuration: CalculatorConfig, environment: Environment) {
        println("Running ${configuration.name}!")
        val calculatorComponent = CalculatorComponent()
        environment.jersey().register(calculatorComponent)
    }
}
