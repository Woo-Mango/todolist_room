package js.pekah.basictodolist.config

import android.app.Application
import js.pekah.basictodolist.repository.TodoRepository

class ApplicationClass: Application() {

    override fun onCreate() {
        super.onCreate()
        TodoRepository.initialize(this)
    }
}