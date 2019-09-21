package com.balcia.binsy.ws.resources.admatachment

import org.jdbi.v3.sqlobject.SqlObject
import org.jdbi.v3.sqlobject.customizer.Bind
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys
import org.jdbi.v3.sqlobject.statement.SqlQuery
import org.jdbi.v3.sqlobject.statement.SqlUpdate

interface AdmAttachmentDAO : SqlObject {
    @SqlUpdate("insert into ADM_ATTACHMENTS (id, path) values (:attachment.id, :attachment.path)")
    @GetGeneratedKeys
    fun insert(attachment: AdmAttachment): Int

    @SqlQuery("select id, path from ADM_ATTACHMENTS where id = :id")
    fun find(@Bind("id") id: Int): AdmAttachment?

    @SqlQuery("select id, path from ADM_ATTACHMENTS")
    fun findAll(): List<AdmAttachment>
}
