package js.pekah.basictodolist.adapter

import android.content.Context

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import js.pekah.basictodolist.R
import js.pekah.basictodolist.dto.Todo

class TodoAdapter(val context: Context): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var list = mutableListOf<Todo>()

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var content = itemView.findViewById<TextView>(R.id.tvJob)
        var checkbox = itemView.findViewById<CheckBox>(R.id.cbIsDone)
        var button = itemView.findViewById<Button>(R.id.btnDelete)
        fun onBind(data: Todo) {
            content.text  = data.content
            checkbox.isChecked = data.isChecked
            button.isActivated = data.isDeleted

            checkbox.setOnClickListener{
                itemCheckBoxClickListener.onClick(it, layoutPosition, list[layoutPosition].id)
            }
/*
            content.setOnClickListener {
                itemClickListener.onClick(it, layoutPosition, list[layoutPosition].id)
            }
*/
            button.setOnClickListener {
                itemButtonClickListener.onClick(it, layoutPosition, list[layoutPosition].id)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_list, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(newList: MutableList<Todo>) {
        this.list = newList
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClick(view: View,  position: Int, itemId: Long)
    }
    interface ItemCheckBoxClickListener {
        fun onClick(view: View, position: Int, itemId: Long)
    }
    interface ItemButtonClickListener {
        fun onClick(view: View, position: Int, itemId: Long)
    }

    private lateinit var itemClickListener: ItemClickListener
    private lateinit var itemCheckBoxClickListener: ItemCheckBoxClickListener
    private lateinit var itemButtonClickListener: ItemButtonClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
    fun setItemCheckBoxClickListener(itemCheckBoxClickListener: ItemCheckBoxClickListener) {
        this.itemCheckBoxClickListener = itemCheckBoxClickListener
    }
    fun setItemButtonClickListener(itemButtonClickListener: ItemButtonClickListener) {
        this.itemButtonClickListener = itemButtonClickListener
    }
}