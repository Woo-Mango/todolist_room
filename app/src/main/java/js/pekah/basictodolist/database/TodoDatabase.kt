package js.pekah.basictodolist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import js.pekah.basictodolist.dao.TodoDao
import js.pekah.basictodolist.dto.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

