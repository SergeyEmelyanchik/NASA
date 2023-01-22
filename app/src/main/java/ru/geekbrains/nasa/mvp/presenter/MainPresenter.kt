package ru.geekbrains.nasa.mvp.presenter

import moxy.MvpPresenter
import ru.geekbrains.nasa.mvp.view.MainView
import ru.geekbrains.nasa.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router : Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(Screens.MarsPhotos())
    }

    fun backClicked() {
        router.exit()
    }

}