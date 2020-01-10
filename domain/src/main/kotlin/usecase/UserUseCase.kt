package usecase

import adapter.UserRepositoryAdapter
import model.User

class UserUseCase(private val userRepository: UserRepositoryAdapter) {

    fun create(name: String): User? {
        return userRepository.create(name)
    }

    fun read(): List<User> {
        return userRepository.read()
    }

    fun read(id: Int): User? {
        return userRepository.read(id)
    }

    fun update(id: Int, name: String): User? {
        return userRepository.update(id, name)
    }

    fun delete(id: Int) {
        userRepository.delete(id)
    }
}