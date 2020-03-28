package com.example.formmessage.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.formmessage.databinding.ActivityMainBinding
import com.example.formmessage.viewmodel.MainActivityModel
import com.example.formmessage.binding.bindMessageRecordList

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get the ViewModel for the main Activity.
        val viewModel = ViewModelProvider(this).get(MainActivityModel::class.java)

        // Bind the message list with the recycle view.
        ActivityMainBinding.inflate(layoutInflater).let {
            it.messageList.bindMessageRecordList(this, viewModel.messageList)
            setContentView(it.root)
        }
    }
}
