import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.*
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import kotlinx.serialization.Serializable

@UseExperimental(KtorExperimentalLocationsAPI::class)
fun Route.userController(useCase: UserUseCase) {
    route("users") {
        get {
            call.respond(useCase.read())
        }

        @Location("{id}")
        data class UserGetArgs(val id: Int)
        get<UserGetArgs> {
            val user = useCase.read(it.id)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(user)
            }
        }

        @Location("")
        class UserCreateArgs

        @Serializable
        data class UserCreateBody(val name: String)
        post<UserCreateArgs> {
            val body = call.receive<UserCreateBody>()
            val user = useCase.create(body.name)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(user)
            }
        }

        @Location("")
        class UserUpdateArgs

        @Serializable
        data class UserUpdateBody(val id: Int, val name: String)
        put<UserUpdateArgs> {
            val body = call.receive<UserUpdateBody>()
            val user = useCase.update(body.id, body.name)
            if (user == null) {
                call.respond(HttpStatusCode.NotFound)
            } else {
                call.respond(user)
            }
        }

        @Location("{id}")
        data class UserDeleteArgs(val id: Int)
        delete<UserDeleteArgs> {
            useCase.delete(it.id)
            call.respond(Message("Deleted."))
        }
    }
}