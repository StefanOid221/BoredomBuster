package com.example.boredombuster.Model

import androidx.room.Database
import kotlin.reflect.KClass
import kotlin.reflect.KFunction4

open class SingletonHolder<out T, in A>(private val constructor: (A) -> T) {
    @Volatile
    private var instance: T? = null
    fun getInstance(arg: A): T =
        instance ?: synchronized(this) {
            instance ?: constructor(arg).also { instance = it}
        }
}