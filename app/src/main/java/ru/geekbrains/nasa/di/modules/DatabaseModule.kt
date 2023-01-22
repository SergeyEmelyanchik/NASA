package ru.geekbrains.nasa.di.modules

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.nasa.App
import ru.geekbrains.nasa.mvp.model.ModelData
import ru.geekbrains.nasa.mvp.model.ModelDataProviders
import ru.geekbrains.nasa.mvp.model.db.DataDb
import ru.geekbrains.nasa.mvp.model.db.room.Database
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App) = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()

    @Singleton
    @Provides
    fun dataDb(database: Database): DataDb {
        return DataDb(database)
    }

    @Singleton
    @Provides
    fun ModelData(): ModelData = ModelDataProviders.newInstance()

}