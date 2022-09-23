package js.pekah.basictodolist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import js.pekah.basictodolist.adapter.TodoAdapter
import js.pekah.basictodolist.databinding.FragmentAllBinding
import js.pekah.basictodolist.viewmodel.TodoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class FragmentAll : Fragment() {

    private lateinit var binding: FragmentAllBinding
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(layoutInflater)
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoAdapter = TodoAdapter(requireActivity())
        binding.rvAll.layoutManager = LinearLayoutManager(activity)
        binding.rvAll.adapter = todoAdapter
        binding.rvAllView.layoutManager = LinearLayoutManager(activity)
        binding.rvAllView.adapter = todoAdapter
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        todoViewModel.allList.observe(viewLifecycleOwner, Observer{
            todoAdapter.update(it)
        })

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
                    todoViewModel.update(todo)
                }
            }
        })
    }
}