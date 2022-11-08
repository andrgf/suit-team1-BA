package com.example.gamesuit.until

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.gamesuit.activity.data.db.model.AppDB
import java.lang.ref.WeakReference

class App : Application() {

    companion object {
        lateinit var context: WeakReference<Context>
        lateinit var appDb: AppDB
    }

    override fun onCreate() {
        super.onCreate()
        context = WeakReference(applicationContext)
        appDb = Room.databaseBuilder(
            applicationContext,
            AppDB::class.java,
            AppDB.DB_NAME
        )
            .allowMainThreadQueries()
            .build()
    }
}