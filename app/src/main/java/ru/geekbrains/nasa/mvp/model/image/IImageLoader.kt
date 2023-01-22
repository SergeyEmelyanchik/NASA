package ru.geekbrains.nasa.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}