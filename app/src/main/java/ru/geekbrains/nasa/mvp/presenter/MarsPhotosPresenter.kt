package ru.geekbrains.nasa.mvp.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.nasa.mvp.model.ModelData
import ru.geekbrains.nasa.mvp.view.MarsPhotosView
import ru.geekbrains.nasa.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MarsPhotosPresenter(
    private val mainThreadScheduler: Scheduler,
    private val modelData: ModelData,
    val marsPhotosListPresenter: MarsPhotosListPresenter
) : MvpPresenter<MarsPhotosView>() {

    private val TAG = "MarsPhotosPresenter"

    private val _liveData = MutableLiveData<ScreenState>()
    private val liveData: LiveData<ScreenState> = _liveData

    @Inject
    lateinit var router: Router

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
                _liveData.value = it.message?.let { it1 -> ScreenState.Error(it1) }
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
                _liveData.value = it.message?.let { it1 -> ScreenState.Error(it1) }
            })
    }


    fun subscribeToLiveData() = liveData

}

sealed class ScreenState {
    data class Error(val error: String) : ScreenState()
}

