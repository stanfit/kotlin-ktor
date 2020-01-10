package adapter

import model.User

interface UserRepositoryAdapter {

    fun create(name: String): User?

    fun read(): List<User>

    fun read(id: Int): User?

    fun update(id: Int, name: String): User?

    fun delete(id: Int)
}