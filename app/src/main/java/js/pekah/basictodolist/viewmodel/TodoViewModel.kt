package js.pekah.basictodolist.viewmodel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import js.pekah.basictodolist.dto.Todo
import js.pekah.basictodolist.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository)
: ViewModel() {
    val todoList: LiveData<MutableList<Todo>>
    val allList: LiveData<MutableList<Todo>>
    val activeList: LiveData<MutableList<Todo>>
    val doneList: LiveData<MutableList<Todo>>
    val deleteList: LiveData<MutableList<Todo>>
    //private val todoRepository: TodoRepository = TodoRepository.get()

    init {
        todoList = todoRepository.list()
        allList = todoRepository.getAllList()
        activeList = todoRepository.getActiveList()
        doneList = todoRepository.getDoneList()
        deleteList = todoRepository.getDeleteList()
    }

    fun getOne(id: Long) = todoRepository.getTodo(id)

    fun insert(dto: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.insert(dto)
    }

    fun update(dto: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.update(dto)
    }

    fun delete(dto: Todo) = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.delete(dto)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        todoRepository.deleteAll()
    }

}