package js.pekah.basictodolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import js.pekah.basictodolist.adapter.TodoAdapter
import js.pekah.basictodolist.adapter.ViewPagerAdapter
import js.pekah.basictodolist.databinding.ActivityMainBinding
import js.pekah.basictodolist.dto.Todo
import js.pekah.basictodolist.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

/*
        todoViewModel.todoList.observe(this) {
            todoAdapter.update(it)
        }
        binding.rvTodoList.layoutManager = LinearLayoutManager(this)
        binding.rvTodoList.adapter = todoAdapter
*/
        binding.button.setOnClickListener {
            val content = binding.input.text.toString()
            if (content.isNotEmpty()){
                val todo = Todo(0, content, false, false)
                CoroutineScope(Dispatchers.IO).launch {
                    todoViewModel.insert(todo)
                    binding.input.setText(null)
                }
            }

            /*
            val intent = Intent(this, EditTodoActivity::class.java).apply {
                putExtra("type", "ADD")
            }
            requestActivity.launch(intent)
            */
        }
/*
        todoAdapter.setItemClickListener(object: TodoAdapter.ItemClickListener {
            override fun onClick(view: View, position: Int, itemId: Long) {
                CoroutineScope(Dispatchers.IO).launch {
                    val todo = todoViewModel.getOne(itemId)
                    val intent = Intent(this@MainActivity, EditTodoActivity::class.java).apply {
                        putExtra("type", "EDIT")
                        putExtra("item", todo)
                    }
                    requestActivity.launch(intent)
                }
            }
        })
*/
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
/*
    private val requestActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            val todo = it.data?.getSerializableExtra("todo") as Todo

            when(it.data?.getIntExtra("flag", -1)) {
                0 -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        todoViewModel.insert(todo)
                    }
                    Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        todoViewModel.update(todo)
                    }
                    Toast.makeText(this, "수정되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
*/
}