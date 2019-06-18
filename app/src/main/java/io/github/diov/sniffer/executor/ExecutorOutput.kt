package io.github.diov.sniffer.executor

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-17.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

data class ExecutorOutput(
    val code: Int,
    val stdout: List<String>
)
