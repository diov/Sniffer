package io.github.diov.sniffer.executor

import io.github.diov.sniffer.persistence.Destination
import io.github.diov.sniffer.persistence.Profile

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-19.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

object CommandParser {
    fun parse(profile: Profile): String {
        return buildString {
            append(Executable.TCPDUMP.absolutePath).append(' ')
            append("-i ${profile.`interface`}").append(' ')
            if (null != profile.infoLevel) {
                append("-${profile.infoLevel.name.toLowerCase()}").append(' ')
            }
            if (null != profile.snapLength) {
                append("-s ${profile.snapLength}").append(' ')
            }
            if (null != profile.captureCount) {
                append("-c ${profile.captureCount}").append(' ')
            }
            when (profile.destination) {
                is Destination.File -> {
                    append("-w ${profile.destination.path}").append(' ')
                    profile.destination.size?.run { append("-C $this") }
                }
                is Destination.Netcat -> {
                    append("-U -w - port 80").append(' ')
                    append("| ").append(Executable.NETCAT.absolutePath).append(" -l ").append(profile.destination.port)
                }
            }
        }
    }
}
