package com.joeweh.routes

import com.joeweh.schemas.User
import com.joeweh.schemas.UserSchema
import com.joeweh.utils.email.SMTP
import com.joeweh.utils.email.templates.Welcome
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    route("/send-email") {
        get("{id?}") {
            SMTP.sendEmail("siracusa.j@northeastern.edu", Welcome("Joey"))

            call.respond(HttpStatusCode.OK)
        }
    }

    route("/users") {
        get("{id?}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
            try {
                val user = UserSchema.getById(id)
                call.respond(HttpStatusCode.OK, user)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.NotFound)
            }
        }

        post {
            val user = call.receive<User>()
            UserSchema.create(user)
            call.respond(HttpStatusCode.Created)
        }

        put("{id?}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
            val user = call.receive<User>()
            UserSchema.update(id, user)
            call.respond(HttpStatusCode.OK)
        }

        delete("{id?}") {
            val id = call.parameters["id"] ?: throw IllegalArgumentException("Invalid ID")
            UserSchema.delete(id)
            call.respond(HttpStatusCode.OK)
        }
    }
}