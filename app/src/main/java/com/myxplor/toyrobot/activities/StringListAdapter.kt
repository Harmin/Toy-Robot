package com.myxplor.toyrobot.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.myxplor.toyrobot.databinding.StringRowBinding

class StringListAdapter: RecyclerView.Adapter<StringListAdapter.StringViewHolder>() {
    private var commandList: List<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = StringRowBinding.inflate(inflater)
        return StringViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(commandList[position])
    }

    override fun getItemCount(): Int {
        return commandList.size
    }

    fun setList(list: List<String>) {
        commandList = list
        notifyDataSetChanged()
    }

    class StringViewHolder(val binding: StringRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(command: String) {
            with(binding){
                cmdTV.text = command
            }
        }
    }
}