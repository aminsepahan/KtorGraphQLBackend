package com.aminktor.repository

import com.aminktor.models.Model

interface RepositoryInterface<T:Model> {
    fun getById(id: String): T
    fun getAll(): List<T>
    fun delete(id: String): Boolean
    fun add(entry: T): T
    fun update(entry: T): T
}