class UserUseCase(private val userRepository: UserRepositoryAdapter) {

    fun create(name: String) {
        userRepository.create(name)
    }

    fun read(): List<User> {
        return userRepository.read()
    }

    fun read(id: Int): User? {
        return userRepository.read(id)
    }

    fun update(id: Int, name: String) {
        userRepository.update(id, name)
    }

    fun delete(id: Int) {
        userRepository.delete(id)
    }
}