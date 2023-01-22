package ru.geekbrains.nasa.mvp.view.list

interface MarsPhotosItemView : IItemView {
    fun setInfo(text: String)
    fun loadImg(url: String)
    fun favoritesImgOn(favorites: Boolean)

}