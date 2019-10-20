package com.balcia.binsy.ws.resources.crmperson

import com.fasterxml.jackson.annotation.JsonFormat
import java.sql.Date

data class CrmPerson (
    val firstName: String?,
    val name: String,
    val registrationCode: String,
    @JsonFormat(pattern = "YYYY-MM-dd", shape = JsonFormat.Shape.STRING)
    val birthDate: Date?
)