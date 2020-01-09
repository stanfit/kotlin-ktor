package repository

import User
import UserRepositoryAdapter
import dao.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository : UserRepositoryAdapter {

    override fun create(name: String): Unit = transaction {
        Users.insert {
            it[Users.name] = name
        }
    }

    override fun read(): List<User> = transaction {
        Users.selectAll()
            .users()
    }

    override fun read(id: Int): User? = transaction {
        Users.select { Users.id eq id }
            .users()
            .firstOrNull()
    }

    override fun update(id: Int, name: String): Unit = transaction {
        Users.update({ Users.id eq id }) {
            it[Users.name] = name
        }
    }

    override fun delete(id: Int): Unit = transaction {
        Users.deleteWhere { Users.id eq id }
    }

    private fun Query.users(): List<User> = map {
        User(
            id = it[Users.id],
            name = it[Users.name]
        )
    }
}