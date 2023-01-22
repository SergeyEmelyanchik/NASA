package ru.geekbrains.nasa.navigation

import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photo
import ru.geekbrains.nasa.ui.fragments.MarsPhotosFragment
import ru.geekbrains.nasa.ui.fragments.PhotoFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class MarsPhotos() : SupportAppScreen() {
        override fun getFragment() = MarsPhotosFragment.newInstance()
    }

    class PhotoScreens(private val photo: Photo) : SupportAppScreen() {
        override fun getFragment() = PhotoFragment.newInstance(photo)
    }
}