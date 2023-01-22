package ru.geekbrains.nasa.mvp.model.image

import android.widget.ImageView
import com.bumptech.glide.Glide
import ru.geekbrains.nasa.mvp.model.image.IImageLoader
import ru.geekbrains.nasa.R

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {

        Glide.with(container.context)
            .load(url)
            .thumbnail(0.5f)
            .fitCenter()
            .error(R.drawable.ic_not_image)
            .into(container)

    }
}