package io.github.diov.sniffer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.diov.sniffer.executor.SnifferExecutor
import io.github.diov.sniffer.persistence.Profile
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-13.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (SnifferExecutor.checkAccess()) {
            setupView()
        }
    }

    private fun setupView() {
        startButton.setOnClickListener {
            GlobalScope.launch {
                SnifferExecutor.use {
                    val result = it.start(Profile.default)
                    if (result.code != SnifferExecutor.TERMINAL_CODE) {
                        println("${result.code}, ${result.stdout}, ${result.stderr}")
                    } else {
                        println("Failed")
                    }
                }
            }
        }
        interfaceButton.setOnClickListener {
            SnifferExecutor.getInterfaces {
                print(this.stdout)
            }
        }
        stopButton.setOnClickListener {
            SnifferExecutor.use {
                it.close()
            }
        }
    }
}
