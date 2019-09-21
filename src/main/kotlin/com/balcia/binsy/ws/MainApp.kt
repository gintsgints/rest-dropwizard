package com.balcia.binsy.ws

import com.balcia.binsy.ws.resources.admatachment.AdmAttachmentDAO
import com.balcia.binsy.ws.resources.admatachment.AdmAttachmentResource
import com.balcia.binsy.ws.resources.calculator.CalculatorComponent
import com.balcia.binsy.ws.resources.root.RootResource
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.dropwizard.Application
import io.dropwizard.jdbi3.JdbiFactory
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import org.jdbi.v3.core.Jdbi


class RestWSApp : Application<RestWSConfig>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            RestWSApp().run(*args)
        }
    }

    override fun run(configuration: RestWSConfig, env: Environment) {
        println("Running ${configuration.appName}!")

        val factory = JdbiFactory()
        val jdbi = factory.build(env, configuration.dataSourceFactory, "postgresql")
        jdbi.installPlugins()
        val dao = jdbi.onDemand(AdmAttachmentDAO::class.java)

        env.jersey().register(AdmAttachmentResource(dao))
        env.jersey().register(RootResource(configuration.appName))
        env.healthChecks().register("default", DefaultHealthCheck())
        val calculatorComponent = CalculatorComponent()
        env.jersey().register(calculatorComponent)
    }

    override fun initialize(bootstrap: Bootstrap<RestWSConfig>) {
        bootstrap.objectMapper.registerModule(KotlinModule())
    }

}
