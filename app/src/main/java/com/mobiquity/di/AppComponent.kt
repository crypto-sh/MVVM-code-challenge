package com.mobiquity.di


import com.core.di.CoreComponent
import com.mobiquity.ui.MainActivity
import dagger.Component


@Component(
    modules = arrayOf(
        AppModule::class
    ),
    dependencies = arrayOf(
        CoreComponent::class
    )
)
@AppScope
interface AppComponent {
    fun inject(target : MainActivity)

}