package io.github.diov.sniffer.executor

import android.util.Log
import com.topjohnwu.superuser.Shell
import io.github.diov.sniffer.misc.Core
import java.io.File

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-18.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

object Executable {
    private const val TAG = "Executable"
    private const val TCPDUMP_BIN = "tcpdump.so"
    private const val NETCAT_BIN = "netcat.so"
    val TCPDUMP = File(
        Core.app.applicationInfo.nativeLibraryDir,
        TCPDUMP_BIN
    )
    val NETCAT = File(
        Core.app.applicationInfo.nativeLibraryDir,
        NETCAT_BIN
    )

    private val EXECUTABLES = setOf("tcpdump.so", "tcpdump.so")
    private val killAllScript = """
        if pgrep $TCPDUMP_BIN; then
            pkill $TCPDUMP_BIN;
        fi &&
        if pgrep $NETCAT_BIN; then
           pkill $NETCAT_BIN;
        fi
    """.trimIndent()

    fun killAll() {
        val result = Shell.su(killAllScript).exec()
        if (!result.isSuccess) {
            Log.w(TAG, "su killall failed")
        }
    }
}
