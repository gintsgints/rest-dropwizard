package com.balcia.binsy.wstest

import com.balcia.binsy.ws.RestWSApp
import io.dropwizard.testing.ResourceHelpers.resourceFilePath
import io.dropwizard.testing.junit.DropwizardAppRule
import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.client.ClientProperties
import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.Assert.assertEquals
import org.junit.ClassRule
import org.junit.Test
import javax.ws.rs.client.Client
import javax.ws.rs.core.Response


class RootResourceIntTest {

    companion object {
        @ClassRule
        @JvmField
        val rule = DropwizardAppRule(
            RestWSApp::class.java,
            resourceFilePath("config.yml")
        )
    }

    @Test
    fun testDefaultResource() {
        val response = client().target("http://127.0.0.1:${rule.localPort}/").request()
            .get(Response::class.java)
        assertEquals(200, response.status.toLong())
        assertEquals("application/json", response.headers.getFirst("Content-Type"))
        val entity = response.readEntity(Map::class.java)
        assertEquals("Kotlin REST WS example", entity["name"])
    }

    private fun client(): Client {
        val config = ClientConfig()
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000)
        config.property(ClientProperties.READ_TIMEOUT, 15000)
        return JerseyClientBuilder.createClient(config)
    }

}
