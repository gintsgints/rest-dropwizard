package com.balcia.binsy.wstest

import com.balcia.binsy.ws.RestWSApp
import com.balcia.binsy.ws.resources.admatachment.AdmAttachment
import io.dropwizard.testing.ResourceHelpers
import io.dropwizard.testing.junit.DropwizardAppRule
import org.glassfish.jersey.client.ClientConfig
import org.glassfish.jersey.client.ClientProperties
import org.glassfish.jersey.client.JerseyClientBuilder
import org.junit.*
import javax.ws.rs.client.Client
import javax.ws.rs.client.Entity
import javax.ws.rs.core.MediaType

class AdmAttachmentResourceTest {
    companion object {
        @ClassRule
        @JvmField
        val rule = DropwizardAppRule(
            RestWSApp::class.java,
            ResourceHelpers.resourceFilePath("config.yml")
        )
    }

    @Test
    fun testShouldCreateAProvider() {
        val attachment = AdmAttachment(id = 0, path = "c:/testdoc")
        val response = client().target("http://localhost:${rule.localPort}/attachment")
            .request()
            .post(Entity.entity(attachment, MediaType.APPLICATION_JSON_TYPE))
        Assert.assertEquals("application/json", response.headers.getFirst("Content-Type"))
        val entity = response.readEntity(Map::class.java)
        Assert.assertEquals("c:/testdoc", entity["path"])
    }

    private fun client(): Client {
        val config = ClientConfig()
        config.property(ClientProperties.CONNECT_TIMEOUT, 5000)
        config.property(ClientProperties.READ_TIMEOUT, 15000)
        return JerseyClientBuilder.createClient(config)
    }

}