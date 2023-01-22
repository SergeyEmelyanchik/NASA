package ru.geekbrains.nasa.mvp.presenter

import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photo
import ru.geekbrains.nasa.mvp.presenter.list.IMarsPhotosListPresenter
import ru.geekbrains.nasa.mvp.view.list.MarsPhotosItemView

class MarsPhotosListPresenter : IMarsPhotosListPresenter {
    val photos = mutableListOf<Photo>()

    override var itemClickListener: ((MarsPhotosItemView) -> Unit)? = null

    override fun getCount() = photos.size

    override fun bindView(view: MarsPhotosItemView) {
        val photo = photos[view.pos]

        val info: String = " ${photo.earthDate}"

        view.setInfo(info)
        photo.imgSrc?.let { view.loadImg(it) }              // проверка на null так как работат с сетью
        view.favoritesImgOn(photo.isFavorites)
    }

    fun update(photosIn: List<Photo>?) {
        photos.clear()
        photosIn?.let { photos.addAll(it) }
    }
}