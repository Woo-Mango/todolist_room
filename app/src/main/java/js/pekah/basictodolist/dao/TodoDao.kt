package js.pekah.basictodolist.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import js.pekah.basictodolist.dto.Todo

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dto: Todo)

    @Query("select * from todoTable where isDeleted ='0'")
    fun getAllList(): LiveData<MutableList<Todo>>

    @Query("select * from todoTable where isChecked ='0' and isDeleted ='0'")
    fun getActiveList(): LiveData<MutableList<Todo>>

    @Query("select * from todoTable where isChecked ='1' and isDeleted ='0'")
    fun getDoneList(): LiveData<MutableList<Todo>>

    @Query("select * from todoTable where isDeleted ='1'")
    fun getDeleteList(): LiveData<MutableList<Todo>>

    @Query("select * from todoTable")
    fun list(): LiveData<MutableList<Todo>>

    @Query("select * from todoTable where id = (:id)")
    fun selectOne(id: Long): Todo

    @Update
    suspend fun update(dto: Todo)

    @Delete
    fun delete(dto: Todo)

    @Query("delete from todoTable")
    fun deleteAll()
}