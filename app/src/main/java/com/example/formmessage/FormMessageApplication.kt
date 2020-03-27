package com.example.formmessage

import android.annotation.SuppressLint
import android.app.Application
import com.example.formmessage.model.MessageModel
import com.example.formmessage.model.ProdMessageModel
import org.koin.core.context.startKoin
import org.koin.dsl.module


@SuppressLint("Registered")
class FormMessageApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single<MessageModel> { ProdMessageModel(this@FormMessageApplication) }
            })
        }
    }
}
