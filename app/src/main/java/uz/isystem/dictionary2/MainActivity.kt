package uz.isystem.dictionary2

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.dictionary2.core.adapters.DictionaryAdapter
import uz.isystem.dictionary2.core.database.DataBase
import uz.isystem.dictionary2.core.dialog.AddDialog
import uz.isystem.dictionary2.core.models.MyDictionary
import uz.isystem.dictionary2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val adapter = DictionaryAdapter()

    private val context = this
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val data = ArrayList<MyDictionary>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val dialog = AddDialog(this)
            dialog.show()

        }

        binding.dictionaryList.adapter = adapter

        binding.dictionaryList.layoutManager = LinearLayoutManager(this)

        loadAllData()
        loadSwipeAction()
        binding.searchView.addTextChangedListener {

            it?.let {
                if (it.toString().trim().isNotEmpty()) {
                    adapter.clearData()
                    val data = DataBase.getDataBase().searchByName(it.toString().trim())
                    adapter.data = data
                } else {
                    adapter.clearData()
                    loadAllData()
                }
            }
        }
    }

    private fun loadSwipeAction() {
        val listener = object :
            ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (direction) {
                    ItemTouchHelper.LEFT -> {
//                        DataBase.getDataBase().deleteDictionary()
                        adapter.notifyItemRemoved(viewHolder.adapterPosition)

                        Toast.makeText(this@MainActivity, "Left", Toast.LENGTH_SHORT).show()
                    }
                    ItemTouchHelper.RIGHT -> {
                        Toast.makeText(this@MainActivity, "Right", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }

        val itemTouchHelper = ItemTouchHelper(listener)

        itemTouchHelper.attachToRecyclerView(binding.dictionaryList)
    }

    private fun loadAllData() {
        data.addAll(DataBase.getDataBase().dictionary)
        adapter.data = data
    }

    fun onClickOne(view: View) {
    }

}
