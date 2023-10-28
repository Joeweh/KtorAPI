package com.joeweh.utils.email

interface EmailTemplate {
    val subject: String

    fun compose(): String
}