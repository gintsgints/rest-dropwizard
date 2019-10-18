package com.balcia.binsy.ws.resources.admatachment

import io.dropwizard.jersey.params.IntParam
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.slf4j.LoggerFactory
import javax.validation.Valid
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/attachment")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class AdmAttachmentResource(override val kodein: Kodein): KodeinAware {
    private val dao: AdmAttachmentDAO by kodein.instance()
    val logger = LoggerFactory.getLogger(AdmAttachmentResource::class.java)

    @GET
    fun getAll(): Response {
        logger.info("Handling request to list providers")
        return Response.ok(dao.findAll()).build()
    }

    @POST
    fun create(@Valid admAttachment: AdmAttachment): Response {
        logger.info("Handling request to create a provider")
        val id = dao.insert(admAttachment)
        val att = dao.find(id)
        return Response.ok(att).build()
    }

    @GET
    @Path("/{id}")
    fun get(@PathParam("id") id: IntParam) : Response {
        logger.info("Handling request to get provider with id: $id")
        val provider = dao.find(id.get())
        if (provider != null) {
            return Response.ok(provider).build()
        }
        logger.error("Provider with id: $id was not found")
        return Response.status(Response.Status.NOT_FOUND).build()
    }
}
