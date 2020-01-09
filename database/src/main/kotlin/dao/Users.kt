package dao

import org.jetbrains.exposed.dao.id.IntIdTable

object Users : IntIdTable("users") {
    val name = varchar("name", 50)
}