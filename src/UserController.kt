package com.stanfit

import io.ktor.application.call
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.UserController() {
    route("users") {
        get {
            call.respond(users)
        }
        get("{id}") {
            val id = call.parameters["id"]?.toInt()
            call.respond(users.first { it.id == id })
        }
        post {
            val user = call.receive<User>()
            call.respond(user)
        }
        delete {
            val user = call.receive<User>()
            call.respond(user)
        }
    }
}