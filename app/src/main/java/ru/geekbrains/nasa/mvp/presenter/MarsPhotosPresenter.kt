package ru.geekbrains.nasa.mvp.presenter

import android.util.Log
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.nasa.mvp.model.ModelData
import ru.geekbrains.nasa.mvp.model.api.nasa.entity.mars.Photo
import ru.geekbrains.nasa.mvp.presenter.list.IMarsPhotosListPresenter
import ru.geekbrains.nasa.mvp.view.MarsPhotosView
import ru.geekbrains.nasa.mvp.view.list.MarsPhotosItemView
import ru.geekbrains.nasa.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MarsPhotosPresenter(
    private val mainThreadScheduler: Scheduler,
    private val modelData: ModelData
) : MvpPresenter<MarsPhotosView>() {

    private val TAG = "MarsPhotosPresenter"

    @Inject
    lateinit var router: Router
    val marsPhotosListPresenter = MarsPhotosListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData(false)

        // Показать подробно
        marsPhotosListPresenter.itemClickListener = { itemView ->
            {}
            router.navigateTo(Screens.PhotoScreens(marsPhotosListPresenter.photos[itemView.pos]))
        }
    }

    fun loadData(isLoadDb: Boolean) {

        modelData.getMarsPhotos(isLoadDb)
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribe({ marsPhotos ->
                marsPhotosListPresenter.update(marsPhotos.getPhotos())
                viewState.updateList()
            }, {
                Log.e(TAG, "Error: ${it.message}");
            })

    }

    fun getBundle() = modelData.getBundle()

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun loadFavorites() {
        modelData.getFavorites()
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribe({ marsPhotos ->
                marsPhotosListPresenter.update(marsPhotos.getPhotos())
                viewState.updateList()
            }, {
                Log.e(TAG, "Error: ${it.message}");
            })
    }


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

}

