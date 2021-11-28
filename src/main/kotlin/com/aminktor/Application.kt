package com.aminktor

import com.aminktor.graphql.dessertSchema
import io.ktor.server.netty.*
import com.apurebase.kgraphql.GraphQL
import io.ktor.application.*

fun main(args: Array<String>): Unit = EngineMain.main(args)


@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(GraphQL) {
        playground = true
        schema {
            dessertSchema()
        }
    }
}