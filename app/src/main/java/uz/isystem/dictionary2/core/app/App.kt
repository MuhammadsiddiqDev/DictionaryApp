package uz.isystem.dictionary2.core.app

import android.app.Application
import uz.isystem.dictionary2.core.database.DataBase

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DataBase.init(this)
    }
}