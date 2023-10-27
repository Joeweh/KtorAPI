package com.joeweh.models

import kotlinx.serialization.Serializable
import java.util.UUID

// TODO make serializer for UUID for id instead of string
@Serializable
data class User(val id: String, val email: String, val password: String)

val users = mutableListOf<User>()