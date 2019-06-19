package io.github.diov.sniffer.executor

import com.topjohnwu.superuser.Shell
import io.github.diov.sniffer.BuildConfig
import io.github.diov.sniffer.persistence.Profile
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-17.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

class SnifferExecutor {
    companion object {
        const val TERMINAL_CODE = 143

        init {
            Shell.Config.setFlags(Shell.FLAG_REDIRECT_STDERR)
            Shell.Config.verboseLogging(BuildConfig.DEBUG)
            Shell.Config.setTimeout(10)
        }

        private var instance: SnifferExecutor? = null
        private fun ensureInstance(): SnifferExecutor {
            var instance = instance
            if (instance == null || !instance.isAlive) instance =
                SnifferExecutor().also { SnifferExecutor.instance = it }
            return instance
        }

        fun <T> use(operation: (SnifferExecutor) -> T) {
            val instance = ensureInstance()
            operation(instance)
        }

        fun checkAccess(): Boolean {
            return Shell.rootAccess()
        }

        fun getInterfaces(callback: (ExecutorOutput.() -> Unit)? = null) {
            val result = Shell.su("${Executable.TCPDUMP.absolutePath} -D").exec()
            callback?.invoke(ExecutorOutput(result.code, result.out, result.err))
        }

        fun stopSniffer() {
            Executable.killAll()
        }
    }

    private val shell = Shell.newInstance("su")
    private val stdout = ArrayList<String>()
    private val stderr = ArrayList<String>()
    private val isAlive get() = shell.isAlive

    fun start(profile: Profile): ExecutorOutput {
        stdout.clear()
        stderr.clear()
        val command = CommandParser.parse(profile)
        val result = shell.newJob()
            .add(command)
            .to(stdout, stderr).exec()
        return ExecutorOutput(result.code, result.out, result.err)
    }

    fun close() {
        stopSniffer()
        GlobalScope.launch {
            delay(100)
            shell.close()
        }
        if (instance == this) instance = null
    }
}
