package js.pekah.basictodolist.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todoTable")
class Todo(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "isChecked") var isChecked: Boolean,
    @ColumnInfo(name = "isDeleted") var isDeleted: Boolean
): Serializable {
}