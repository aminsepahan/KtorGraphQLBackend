package com.aminktor.services

import com.aminktor.models.Dessert
import com.aminktor.models.DessertInput
import com.aminktor.models.DessertsPage
import com.aminktor.repository.DessertRepository
import com.mongodb.client.MongoClient
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class DessertService : KoinComponent {
    private val client: MongoClient by inject()
    private val repo: DessertRepository = DessertRepository(client)

    fun getDessertsPage(page: Int, size: Int): DessertsPage {
        return repo.getDessertsPage(page, size)
    }

    fun getDessert(id: String): Dessert {
        return repo.getById(id)
    }

    fun createDessert(dessetInput: DessertInput, userId: String): Dessert {
        val uid = UUID.randomUUID().toString()
        val dessert = Dessert(
            id = uid,
            userId = userId,
            name = dessetInput.name,
            description = dessetInput.description,
            imageUrl = dessetInput.imageUrl
        )
        return repo.add(dessert)
    }

    fun updateDessert(userId: String, dessertId: String, dessertInput: DessertInput): Dessert {
        val dessert = repo.getById(dessertId)
        if (dessert.userId == userId) {
            val update = Dessert(
                id = dessertId,
                userId = userId,
                name = dessertInput.name,
                description = dessertInput.description,
                imageUrl = dessertInput.imageUrl
            )
            return repo.update(update)
        }
        error("Cannot update dessert")
    }


    fun deleteDessert(userId: String, dessertId: String): Boolean {
        val dessert = repo.getById(dessertId)
        if (dessert.userId == userId) {
            return repo.delete(dessertId)
        }
        error("Cannot delete dessert")
    }

}