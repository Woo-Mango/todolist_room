package js.pekah.basictodolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import js.pekah.basictodolist.adapter.TodoAdapter
import js.pekah.basictodolist.adapter.ViewPagerAdapter
import js.pekah.basictodolist.databinding.ActivityMainBinding
import js.pekah.basictodolist.dto.Todo
import js.pekah.basictodolist.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var todoViewModel: TodoViewModel
    lateinit var todoAdapter: TodoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tabTitleArray = arrayOf("All", "Active", "Done", "Delete")
        val tabLayout = binding.tabLayout

        val viewPager = binding.vpTodo
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoAdapter = TodoAdapter(this)

        binding.button.setOnClickListener {
            val content = binding.input.text.toString()
            if (content.isNotEmpty()){
                val todo = Todo(0, content, false, false)
                CoroutineScope(Dispatchers.IO).launch {
                    todoViewModel.insert(todo)
                    binding.input.setText(null)
                }
            }

        }

        todoAdapter.setItemCheckBoxClickListener(object: TodoAdapter.ItemCheckBoxClickListener {
            override fun onClick(view: View, position: Int, itemId: Long) {
                CoroutineScope(Dispatchers.IO).launch {
                    val todo = todoViewModel.getOne(itemId)
                    todo.isChecked = !todo.isChecked
                    todoViewModel.update(todo)
                }
            }
        })

        todoAdapter.setItemButtonClickListener(object: TodoAdapter.ItemButtonClickListener {
            override fun onClick(view: View, position: Int, itemId: Long) {
                CoroutineScope(Dispatchers.IO).launch {
                    val todo = todoViewModel.getOne(itemId)
                    todo.isDeleted = !todo.isDeleted
                    //todo: delete method modify
                    todoViewModel.update(todo)
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId) {
            R.id.menu_item_delete -> {
                Toast.makeText(this, "전체 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                    todoViewModel.deleteAll()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}