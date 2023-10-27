package com.joeweh.utils.email.templates

import com.joeweh.utils.email.EmailTemplate
import kotlinx.html.*
import kotlinx.html.stream.appendHTML

class Welcome(private val name: String) : EmailTemplate {
    override fun compose(): String {
        val buffer = StringBuffer()

        buffer.appendHTML().html {
            head {
                style {

                }
            }

            body {
                h1 {
                    + "Welcome $name!"
                }
            }
        }

        return buffer.toString()
    }
}