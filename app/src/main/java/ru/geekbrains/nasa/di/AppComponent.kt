package ru.geekbrains.nasa.di

import dagger.Component
import ru.geekbrains.nasa.di.modules.*
import ru.geekbrains.nasa.mvp.model.ModelDataProviders
import ru.geekbrains.nasa.mvp.presenter.MainPresenter
import ru.geekbrains.nasa.mvp.presenter.MarsPhotosPresenter
import ru.geekbrains.nasa.mvp.presenter.PhotoPresenter
import ru.geekbrains.nasa.ui.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(marsPhotosPresenter: MarsPhotosPresenter)
    fun inject(photoPresenter: PhotoPresenter)

    fun inject(modelDataProviders: ModelDataProviders)

}