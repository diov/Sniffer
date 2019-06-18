package io.github.diov.sniffer.model

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-18.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

data class Profile(
    val id: Int,
    val `interface`: String,
    val infoLevel: InfoLevel,
    val destination: Destination,
    val snaplen: Boolean
)

enum class Destination {
    FILE,
    NETCAT
}

enum class InfoLevel {
    Q,
    V,
    VV,
    VVV
}
