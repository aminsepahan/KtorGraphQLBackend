package com.aminktor.repository

import com.aminktor.data.desserts
import com.aminktor.models.Dessert
import io.ktor.features.*

class DessertRepository : RepositoryInterface<Dessert> {
    override fun getById(id: String): Dessert {
        return try {
            desserts.find { it.id == id } ?: throw NotFoundException("No dessert with that ID")
        } catch (e: Exception) {
            throw Exception("cannot find dessert")
        }
    }

    override fun getAll(): List<Dessert> {
        return desserts
    }

    override fun delete(id: String): Boolean {
        return try {
            val dessert = desserts.find { it.id == id } ?: throw NotFoundException("No dessert with that ID")
            desserts.remove(dessert)
            true
        } catch (e: Exception) {
            throw Exception("cannot find dessert")
        }
    }

    override fun add(entry: Dessert): Dessert {
        desserts.add(entry)
        return entry
    }

    override fun update(entry: Dessert): Dessert {
        return try {
            val dessert = desserts.find { it.id == entry.id }?.apply {
                name = entry.name
                description = entry.description
                imageUrl = entry.imageUrl
            } ?: throw NotFoundException("No dessert with that ID")
            dessert
        } catch (e: Exception) {
            throw Exception("cannot find dessert")
        }
    }
}