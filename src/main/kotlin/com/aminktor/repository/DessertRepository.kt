package com.aminktor.repository

import com.aminktor.models.Dessert
import com.aminktor.models.DessertsPage
import com.aminktor.models.PagingInfo
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import org.litote.kmongo.getCollection

class DessertRepository(client: MongoClient) : RepositoryInterface<Dessert> {
    override lateinit var col: MongoCollection<Dessert>

    init {
        val database = client.getDatabase("test")
        col = database.getCollection<Dessert>("Dessert")
    }

    fun getDessertsPage(page: Int, size: Int): DessertsPage {
        try {
            val skips = page * size
            val res = col.find().skip(skips).limit(size)
            val results = res.asIterable().map { it }
            val totalDessert = col.estimatedDocumentCount()
            val totalPages = (totalDessert / size) + 1
            val next = if (results.isNotEmpty()) page + 1 else null
            val prev = if (page > 0) page - 1 else null
            val info = PagingInfo(totalDessert.toInt(), totalPages.toInt(), next, prev)
            return DessertsPage(results, info)
        } catch (e: Exception) {
            throw Exception("Cannot get pages")
        }
    }

}