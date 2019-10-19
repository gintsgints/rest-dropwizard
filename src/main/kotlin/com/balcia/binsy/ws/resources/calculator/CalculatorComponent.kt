package com.balcia.binsy.ws.resources.calculator

import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.ws.rs.WebApplicationException

@Path("/")
@Api(description = "Example calculator API")
class CalculatorComponent {
    @Path("/add")
    @ApiOperation("Returns sum of two numbers")
    @GET
    fun add(@QueryParam("a") a: Double, @QueryParam("b") b: Double): Double {
        return a + b
    }

    @Path("/multiply")
    @ApiOperation(value = "Returns multiply of two numbers")
    @GET
    fun multiply(@QueryParam("a") a: Double, @QueryParam("b") b: Double): Double {
        return a * b
    }

    @Path("/divide")
    @ApiOperation(value = "Returns division of two numbers")
    @GET
    fun divide(@QueryParam("a") a: Double, @QueryParam("b") b: Double): Double {
        if (b == .0) {
            throw WebApplicationException("Can't divide by zero")
        }
        return a / b
    }
}
