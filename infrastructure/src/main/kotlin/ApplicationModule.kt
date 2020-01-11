import adapter.UserRepositoryAdapter
import org.koin.dsl.module
import repository.UserRepository
import usecase.UserUseCase

/**
 * Application's module for dependency injection.
 */
val module = module(createdAtStart = true) {
    // Domain
    factory { UserUseCase(get()) }
    // Database
    single<UserRepositoryAdapter> { UserRepository() }
}