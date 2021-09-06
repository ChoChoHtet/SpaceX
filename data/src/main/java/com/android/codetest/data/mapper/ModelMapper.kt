package com.android.codetest.data.mapper

interface ModelMapper<E, M> {
    fun map(entity: E): M
    fun map(entities: List<E>): List<M> {
        return entities.map { map(it) }
    }
}