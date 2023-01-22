package ru.geekbrains.nasa.mvp.model

import android.os.Bundle
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.nasa.App
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photo
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photos
import ru.geekbrains.nasa.mvp.model.network.AndroidNetworkStatus
import retrofit.ApiHolder
import retrofit.mvp.model.api.nasa.retrofit.INasaMarsPhotos
import ru.geekbrains.nasa.mvp.model.api.nasa.retrofit.RetrofitNasaPhotos
import ru.geekbrains.nasa.mvp.model.db.DataDb
import javax.inject.Inject

class ModelDataProviders : ModelData {
    // Работа с данными
    @Inject
    lateinit var dataDb: DataDb
    private val bundle = Bundle()

    private val dataApi: INasaMarsPhotos = RetrofitNasaPhotos(ApiHolder().api)

    override fun getMarsPhotos(isLoadDb: Boolean): Single<Photos> {


        if (!isLoadDb && networkStatus) {
            val photos = dataApi.getPhotos()
            dataDb.photosUpdate(photos)
            return photos
        } else {
            return dataDb.getPhotos()
        }

    }

    override fun getBundle(): Bundle = bundle
    override fun updatePhoto(photo: Photo) {
        dataDb.updatePhoto(photo)
    }

    override fun getFavorites(): Single<Photos> {
        return dataDb.getFavorites()
    }

    companion object {
        var networkStatus = false
        fun newInstance() = ModelDataProviders().apply {
            App.instance.appComponent.inject(this)
            initNetMonitor()
        }

        fun initNetMonitor() {
            AndroidNetworkStatus(App.instance).isOnline().subscribe {
                networkStatus = it
            }
        }
    }

}





