package ir.example.app.di.scope

import javax.inject.Qualifier

/**
 * It is dagger's annotation's scope for application lifecycle
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ForApplication