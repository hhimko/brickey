package com.example.brickey.di

import kotlin.reflect.KClass


internal object DependencyServices {
    private val instances = mutableMapOf<KClass<*>, InstanceScope<*>>()


    inline fun <reified T: Any> addTransient(noinline creator: () -> T) {
        addFactory(createTransient(creator))
    }

    inline fun <reified T: Any> addSingleton(instance: T) {
        addFactory(createSingleton{ instance })
    }

    inline fun <reified T: Any> addSingleton(noinline creator: () -> T) {
        addFactory(createSingleton(creator))
    }

    inline fun <reified T: Any> getInstance(): T {
        return when(val factory = instances[T::class]) {
            is InstanceScope.TransientFactory -> factory.create() as T
            is InstanceScope.SingletonFactory -> factory.instance as T
            null -> error("No factory provided for class: ${T::class.java}")
        }
    }

    private inline fun <reified T : Any> addFactory(factory: InstanceScope<T>) {
        check(instances[T::class] == null) {
            "Definition for ${T::class::java} has already been added"
        }

        instances[T::class] = factory
    }

    private fun <T: Any> createTransient(creator: () -> T): InstanceScope.TransientFactory<T> {
        return InstanceScope.TransientFactory<T> { creator() }
    }

    private fun <T: Any> createSingleton(creator: () -> T): InstanceScope.SingletonFactory<T> {
        return InstanceScope.SingletonFactory<T> { creator() }
    }

    sealed interface InstanceScope<T> {
        // Transient dependencies are recreated every time they are requested
        fun interface TransientFactory<T>: InstanceScope<T> {
            fun create(): T
        }

        // Singleton dependencies are created once and reused for every request
        class SingletonFactory<T>(private val factory: TransientFactory<T>): InstanceScope<T> {
            val instance: T by lazy { factory.create() }
        }
    }
}
