package com.balcia.binsy.ws.resources.crmperson

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.config.RegisterRowMapper
import org.jdbi.v3.sqlobject.statement.SqlQuery

interface CrmPersonDAO : SqlObject {
    @SqlQuery("select FIRST_NAME, NAME, REGISTRATION_CODE, BIRTH_DATE from CRM_PERSON")
    @RegisterRowMapper(CrmPersonMapper::class)
    fun findAll(): List<CrmPerson>
}
