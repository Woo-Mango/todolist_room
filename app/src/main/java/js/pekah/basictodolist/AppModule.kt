package js.pekah.basictodolist

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import js.pekah.basictodolist.dao.TodoDao
import js.pekah.basictodolist.database.TodoDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getTodoDatabase(app: Application): TodoDatabase =
        Room.databaseBuilder(app, TodoDatabase::class.java,"todo-database.db").build()

    @Provides
    @Singleton
    fun getTodoDao(todoDao: TodoDatabase) : TodoDao = todoDao.todoDao()

}