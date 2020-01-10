import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.*

fun Route.UserController(useCase: UserUseCase) {
    route("users") {
        get {
            call.respond(useCase.read())
        }
        get("{id}") {
            val id = call.parameters["id"]?.toInt()
            if (id == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            val user = useCase.read(id)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            return@get call.respond(user)
        }
        post {
            val user = call.receive<UserArgs>()
            useCase.create(user.name)
            call.respondText { "Created." }
        }
        delete("{id}") {
            val id = call.parameters["id"]?.toInt()
            if (id == null) {
                call.respond(HttpStatusCode.NotFound)
                return@delete
            }
            useCase.delete(id)
            call.respondText { "Deleted." }
        }
    }
}