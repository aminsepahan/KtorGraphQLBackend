package com.aminktor

import com.aminktor.di.mainModule
import com.aminktor.graphql.dessertSchema
import com.aminktor.services.DessertService
import io.ktor.server.netty.*
import com.apurebase.kgraphql.GraphQL
import io.ktor.application.*
import org.koin.core.context.startKoin
import org.koin.ktor.ext.modules

fun main(args: Array<String>): Unit = EngineMain.main(args)


@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    startKoin {
        modules(mainModule)
    }

    install(GraphQL) {
        val dessertService = DessertService()
        playground = true
        schema {
            dessertSchema(dessertService)
        }
    }
}