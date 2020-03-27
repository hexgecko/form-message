package com.example.formmessage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.formmessage.databinding.ActivityDetailBinding
import com.example.formmessage.viewmodel.DetailActivityModel
import android.text.format.DateFormat
import android.view.Menu
import android.view.MenuItem
import com.example.formmessage.viewmodel.DetailActivityModelFactory

class DetailActivity: AppCompatActivity() {
    companion object {
        const val ARG_MESSAGE_ID = "arg_message_id"
    }

    private lateinit var viewModel: DetailActivityModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val messageId = intent.getStringExtra(ARG_MESSAGE_ID)
        val dateFormat = DateFormat.getDateFormat(this)

        viewModel = ViewModelProvider(this, DetailActivityModelFactory(messageId))
            .get(DetailActivityModel::class.java)


        ActivityDetailBinding.inflate(layoutInflater).apply {
            dateText.text = dateFormat.format(viewModel.message?.timestamp ?: 0)
            emailText.text = viewModel.message?.email
            subjectText.text = viewModel.message?.subject
            messageText.text = viewModel.message?.message
            setContentView(root)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> finish()
            R.id.delete -> {
                viewModel.onDeleteMessage()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail, menu)
        return true
    }
}
