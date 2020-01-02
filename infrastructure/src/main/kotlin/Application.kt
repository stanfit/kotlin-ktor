import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.routing.routing
import io.ktor.serialization.DefaultJsonConfiguration
import io.ktor.serialization.serialization
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    embeddedServer(Netty, 8080) {
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
        routing {
            UserController()
        }
    }.start(wait = true)
}