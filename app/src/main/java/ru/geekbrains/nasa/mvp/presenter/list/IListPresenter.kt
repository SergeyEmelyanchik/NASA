package ru.geekbrains.nasa.mvp.presenter.list

import ru.geekbrains.nasa.mvp.view.list.IItemView

interface IListPresenter<V : IItemView> {
    var itemClickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}