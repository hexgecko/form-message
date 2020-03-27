package com.example.formmessage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.formmessage.databinding.ActivityMainBinding
import com.example.formmessage.databinding.ItemMessageBinding
import com.example.formmessage.model.MessageRecord
import com.example.formmessage.viewmodel.MainActivityModel
import android.text.format.DateFormat

class MessageRecordAdapter(
    private val activity: AppCompatActivity,
    private var list: List<MessageRecord>?)
    : RecyclerView.Adapter<MessageRecordAdapter.ItemHolder>()
{
    class ItemHolder(container: ViewGroup, val binding: ItemMessageBinding)
        : RecyclerView.ViewHolder(container)

    private val dateFormat = DateFormat.getDateFormat(activity)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemHolder(binding.root as ViewGroup, binding)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        list?.elementAt(position)?.let { item ->
            holder.binding.apply {
                dateText.text = dateFormat.format(item.timestamp)
                emailText.text = item.email
                subjectText.text = item.subject
                root.setOnClickListener {
                    activity.startActivity(Intent(activity, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.ARG_MESSAGE_ID, item.id)
                    })
                }
            }
        }
    }

    override fun getItemCount(): Int = list?.size ?: 0

    fun updateList(newList: List<MessageRecord>) {
        list = newList
        notifyDataSetChanged()
    }
}

fun RecyclerView.bindMessageRecordList(owner: AppCompatActivity, list: LiveData<List<MessageRecord>>) {

    val adapter = MessageRecordAdapter(owner, list.value)

    this.setHasFixedSize(true)
    this.layoutManager = LinearLayoutManager(owner)
    this.adapter = adapter

    list.observe(owner, Observer { adapter.updateList(it) })
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this).get(MainActivityModel::class.java)

        ActivityMainBinding.inflate(layoutInflater).let {
            it.messageList.bindMessageRecordList(this, viewModel.messageList)
            setContentView(it.root)
        }
    }
}
