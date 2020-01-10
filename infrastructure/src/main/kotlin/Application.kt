import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Locations
import io.ktor.routing.Routing
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json
import repository.UserRepository

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
@UseExperimental(KtorExperimentalLocationsAPI::class)
fun Application.module(testing: Boolean = false) {
    embeddedServer(Netty, 8080) {
        Database.connect()
        Database.create()
        install(Locations)
        install(CallLogging)
        install(ContentNegotiation) {
            serialization(
                contentType = ContentType.Application.Json,
                json = Json(
                    DefaultJsonConfiguration.copy(
                        prettyPrint = true
                    )
                )
            )
        }
        install(Routing) {
            val repository: UserRepositoryAdapter = UserRepository()
            val useCase = UserUseCase(repository)
            userController(useCase)
        }
    }.start(wait = true)
}