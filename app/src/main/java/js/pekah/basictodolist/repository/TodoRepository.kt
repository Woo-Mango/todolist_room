package js.pekah.basictodolist.repository


import androidx.lifecycle.LiveData
import js.pekah.basictodolist.dao.TodoDao
import js.pekah.basictodolist.dto.Todo
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

private const val DATABASE_NAME = "todo-database.db"

class TodoRepository @Inject constructor(private val todoDao: TodoDao){
/*
    private val database: TodoDatabase = Room.databaseBuilder(
        context.applicationContext,
        TodoDatabase::class.java,
        DATABASE_NAME
    ).build()
*/
//  private val todoDao = database.todoDao()
    private var allList: LiveData<MutableList<Todo>> = todoDao.getAllList()
    private var activeList: LiveData<MutableList<Todo>> = todoDao.getActiveList()
    private var doneList: LiveData<MutableList<Todo>> = todoDao.getDoneList()
    private var deleteList: LiveData<MutableList<Todo>> = todoDao.getDeleteList()

    fun getAllList(): LiveData<MutableList<Todo>> {
        return allList
    }
    fun getActiveList(): LiveData<MutableList<Todo>> {
        return activeList
    }
    fun getDoneList(): LiveData<MutableList<Todo>> {
        return doneList
    }
    fun getDeleteList(): LiveData<MutableList<Todo>> {
        return deleteList
    }

    fun desclist(): Flow<List<Todo>> = todoDao.desclist()

    fun list(): LiveData<MutableList<Todo>> = todoDao.list()

    fun getTodo(id: Long): Todo = todoDao.selectOne(id)

    fun insert(dto: Todo) = todoDao.insert(dto)

    suspend fun update(dto: Todo) = todoDao.update(dto)

    fun delete(dto: Todo) = todoDao.delete(dto)

    fun deleteAll() = todoDao.deleteAll()
/*
    companion object {
        private var INSTANCE: TodoRepository?=null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = TodoRepository(context)
            }
        }

        fun get(): TodoRepository {
            return INSTANCE ?:
            throw IllegalStateException("TodoRepository must be initialized")
        }
    }
    */
}