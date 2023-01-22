package ru.geekbrains.nasa.mvp.model

import android.os.Bundle
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photo
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photos

interface ModelData {
   fun getMarsPhotos(isLoadDb: Boolean): Single<Photos>
   fun getBundle(): Bundle
   fun updatePhoto(photo: Photo)
   fun getFavorites(): Single<Photos>
}