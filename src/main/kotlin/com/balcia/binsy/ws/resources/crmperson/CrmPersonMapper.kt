package com.balcia.binsy.ws.resources.crmperson

import org.jdbi.v3.core.mapper.RowMapper
import org.jdbi.v3.core.statement.StatementContext
import java.sql.Date
import java.sql.ResultSet

class CrmPersonMapper : RowMapper<CrmPerson> {
    override fun map(rs: ResultSet?, ctx: StatementContext?): CrmPerson? {
        if (rs != null) {
            return CrmPerson(
                rs.getString("FIRST_NAME"),
                rs.getString("NAME"),
                rs.getString("REGISTRATION_CODE"),
                rs.getDate("BIRTH_DATE")
            )
        } else {
            return null
        }
    }
}