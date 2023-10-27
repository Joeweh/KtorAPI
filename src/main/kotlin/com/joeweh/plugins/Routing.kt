package com.joeweh.plugins

import com.joeweh.routes.userRouting
import com.joeweh.utils.email.SMTP
import com.joeweh.utils.email.templates.Welcome
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    routing {
        get("/ping") {
            println("Port: " + System.getenv()["PORT"])

            call.respondText("Pong")
        }

        userRouting()
    }
}

