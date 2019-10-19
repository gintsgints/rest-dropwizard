package com.balcia.binsy.ws.resources.root

import com.balcia.binsy.ws.RestWSConfig
import com.balcia.binsy.ws.resources.admatachment.AdmAttachmentDAO
import com.codahale.metrics.annotation.Timed
import com.google.common.collect.ImmutableMap
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/")
@Api(description = "Dropwizard Swagger", protocols = "http")
class RootResource(override val kodein: Kodein): KodeinAware {
    private val config: RestWSConfig by kodein.instance()

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @ApiOperation(value = "Returns api information")
    fun default(): Response {
        val entity = ImmutableMap.builder<String, Any>()
            .put("name", config.appName)
            .put("java.version", System.getProperty("java.version"))
            .put("java.runtime.name", System.getProperty("java.runtime.name"))
            .build()
        return Response.ok().entity(entity).build()
    }
}
