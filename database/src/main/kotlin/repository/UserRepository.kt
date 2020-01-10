package repository

import adapter.UserRepositoryAdapter
import dao.Users
import model.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository : UserRepositoryAdapter {

    override fun create(name: String): User? = transaction {
        val id = Users.insertAndGetId {
            it[Users.name] = name
        }
        read(id.value)
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

    override fun update(id: Int, name: String): User? = transaction {
        Users.update({ Users.id eq id }) {
            it[Users.name] = name
        }
        read(id)
    }

    override fun delete(id: Int): Unit = transaction {
        Users.deleteWhere { Users.id eq id }
    }

    private fun Query.users(): List<User> = map {
        User(
            id = it[Users.id].value,
            name = it[Users.name]
        )
    }
}