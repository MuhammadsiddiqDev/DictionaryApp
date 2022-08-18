package uz.isystem.dictionary2.core.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.isystem.dictionary2.core.models.MyDictionary
import uz.isystem.dictionary2.databinding.ItemDictionaryBinding

class DictionaryAdapter : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    var data = ArrayList<MyDictionary>()
        set(value) {
            field.addAll(value)
            notifyItemRangeInserted(field.size - value.size, value.size)
        }

    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemDictionaryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun binData(myDictionary: MyDictionary) {
            binding.itemEng.text = myDictionary.eng
            binding.itemUzb.text = myDictionary.uzb
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemDictionaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.binData(data[position])

    override fun getItemCount(): Int = data.size
}