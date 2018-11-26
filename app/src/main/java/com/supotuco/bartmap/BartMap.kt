package com.supotuco.bartmap

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.supotuco.bartmap.model.BartREST
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class BartMap : AppCompatActivity() {

    private lateinit var textView: TextView
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bart_map)

        textView = findViewById(R.id.textView)
    }

    override fun onStart() {
        super.onStart()

        disposable = BartREST.service()
                .stations(BartREST.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = { response ->
                            textView.text = response.toString()
                        },
                        onError = { error ->
                            textView.text = error.message
                        }
                )
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
