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
import io.federecio.dropwizard.swagger.SwaggerBundle
import org.flywaydb.core.Flyway
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration




class RestWSApp : Application<RestWSConfig>() {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            RestWSApp().run(*args)
        }
    }

    override fun run(configuration: RestWSConfig, env: Environment) {
        Flyway.configure().dataSource(
            configuration.dataSourceFactory.url,
            configuration.dataSourceFactory.user,
            configuration.dataSourceFactory.password
        )
            .encoding("UTF-8")
            .load()
            .migrate()

        // Initialize JDBI
        val factory = JdbiFactory()
        val jdbi = factory.build(env, configuration.dataSourceFactory, "postgresql")
        jdbi.installPlugins()
        val dao = jdbi.onDemand(AdmAttachmentDAO::class.java)

        val kodein = Kodein {
            bind<AdmAttachmentDAO>() with singleton { dao }
            bind<RestWSConfig>() with singleton { configuration }
        }

        env.jersey().register(AdmAttachmentResource(kodein))
        env.jersey().register(RootResource(kodein))
        env.healthChecks().register("default", DefaultHealthCheck())
        val calculatorComponent = CalculatorComponent()
        env.jersey().register(calculatorComponent)
    }

    override fun initialize(bootstrap: Bootstrap<RestWSConfig>) {
        bootstrap.objectMapper.registerModule(KotlinModule())
        bootstrap.addBundle(object : SwaggerBundle<RestWSConfig>() {
            override fun getSwaggerBundleConfiguration(configuration: RestWSConfig): SwaggerBundleConfiguration? {
                return configuration.swaggerBundleConfiguration
            }
        })
    }

}
