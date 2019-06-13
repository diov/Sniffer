package io.github.diov.sniffer

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.postDelayed
import com.topjohnwu.superuser.Shell
import java.io.File

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

        confirmRootAccess()
    }

    private fun confirmRootAccess() {
        Handler().postDelayed(1000) {
            val execatable = File(applicationInfo.nativeLibraryDir, "netcat.so").absolutePath
            val output = Shell.su(execatable).exec().out
            println(output)
        }
    }

    companion object {
        init {
            Shell.Config.setFlags(Shell.FLAG_REDIRECT_STDERR)
            Shell.Config.verboseLogging(BuildConfig.DEBUG)
            Shell.Config.setTimeout(10)
        }
    }
}
