package js.pekah.basictodolist.config

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import js.pekah.basictodolist.repository.TodoRepository
import javax.inject.Inject

@HiltAndroidApp
class ApplicationClass: Application() {

    override fun onCreate() {
        super.onCreate()
        //TodoRepository.initialize(this)
    }

}