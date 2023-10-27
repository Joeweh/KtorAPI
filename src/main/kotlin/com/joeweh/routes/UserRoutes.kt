package com.joeweh.routes

import com.joeweh.models.User
import com.joeweh.models.users
import com.joeweh.utils.email.SMTP
import com.joeweh.utils.email.templates.Welcome
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.h2.value.TypeInfo

fun Route.userRouting() {
    route("/send-email") {
        get("{id?}") {
            SMTP.sendEmail("siracusa.j@northeastern.edu", Welcome("Joey"))

            call.respond(HttpStatusCode.OK)
        }
    }

    route("/users") {
        get {
            call.respond(HttpStatusCode.OK, users)
        }

        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                "Missing id"
            )

            val user = users.find { it.id == id } ?: return@get call.respond(
                HttpStatusCode.NotFound,
                "User with id $id not found"
            )

            call.respond(HttpStatusCode.OK, user)
        }

        post {
            val user = call.receive<User>()

            users.add(user)

            call.respond(HttpStatusCode.Created, "user stored correctly")
        }

        put("{id?}") {
            val id = call.parameters["id"] ?: return@put call.respond(
                HttpStatusCode.BadRequest,
                "Missing id"
            )

            val user = call.receive<User>()

            //users.set()
        }

        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(
                HttpStatusCode.BadRequest,
                "Missing id"
            )

            val hasChanged = users.removeIf { it.id == id }

            if (!hasChanged) {
                return@delete call.respond(HttpStatusCode.NoContent, "no user with id $id")
            }

            call.respond(HttpStatusCode.OK, "user deleted")
        }
    }
}