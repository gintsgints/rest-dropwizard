package com.balcia.binsy.ws.resources.crmperson

import com.balcia.binsy.ws.resources.admatachment.AdmAttachmentResource
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.slf4j.LoggerFactory
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Api(description = "Attachment API")
class CrmPersonResource(override val kodein: Kodein): KodeinAware {

    private val dao: CrmPersonDAO by kodein.instance()
    val logger = LoggerFactory.getLogger(AdmAttachmentResource::class.java)

    @GET
    @ApiOperation("Returns list of all persons")
    fun getAll(): Response {
        logger.info("Query for all persons")
        return Response.ok(dao.findAll()).build()
    }
}