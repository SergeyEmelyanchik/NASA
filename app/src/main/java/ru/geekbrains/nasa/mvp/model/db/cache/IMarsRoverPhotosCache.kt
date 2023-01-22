package ru.geekbrains.nasa.mvp.model.db.cache

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photo
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photos

interface IMarsRoverPhotosCache {
    fun getPhotos(): Single<Photos>
    fun photosUpdate(photos: Single<Photos>)
    fun updatePhoto(photo: Photo)
    fun getFavorites(): Single<Photos>
}