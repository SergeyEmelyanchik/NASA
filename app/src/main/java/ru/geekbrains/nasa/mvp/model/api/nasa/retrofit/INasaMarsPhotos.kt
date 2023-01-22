package retrofit.mvp.model.api.nasa.retrofit

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photos

interface INasaMarsPhotos {
    fun getPhotos(): Single<Photos>
    fun getPhotos(urlRepo: String): Single<Photos>
}