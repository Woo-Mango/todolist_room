package js.pekah.basictodolist.adapter

import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import js.pekah.basictodolist.R
import js.pekah.basictodolist.dto.Todo
import js.pekah.basictodolist.fragment.FragmentActive
import js.pekah.basictodolist.fragment.FragmentAll
import js.pekah.basictodolist.fragment.FragmentDelete
import js.pekah.basictodolist.fragment.FragmentDone

private const val NUM_TABS = 4


class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private var list = mutableListOf<Todo>()

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return FragmentAll()
            1 -> return FragmentActive()
            2 -> return FragmentDone()
            3 -> return FragmentDelete()
        }
        return FragmentAll()
    }


    override fun getItemCount(): Int {
        return NUM_TABS
    }

    fun update(newList: MutableList<Todo>) {
        this.list = newList
        notifyDataSetChanged()
    }

}