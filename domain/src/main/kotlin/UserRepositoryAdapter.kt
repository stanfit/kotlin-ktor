interface UserRepositoryAdapter {

    fun create(name: String)

    fun read(): List<User>

    fun read(id: Int): User?

    fun update(id: Int, name: String)

    fun delete(id: Int)
}