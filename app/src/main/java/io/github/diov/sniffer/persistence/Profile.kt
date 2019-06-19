package io.github.diov.sniffer.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-18.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "interface") val `interface`: String,
    @ColumnInfo(name = "infoLevel") val infoLevel: InfoLevel?,
    @ColumnInfo(name = "destination") val destination: Destination,
    @ColumnInfo(name = "snapLength") val snapLength: Int?,
    @ColumnInfo(name = "captureCount") val captureCount: Long?
) {
    companion object {
        val default: Profile = Profile(
            id = null,
            infoLevel = null,
            `interface` = "any",
            destination = Destination.Netcat(31337),
            snapLength = 0,
            captureCount = null
        )
    }
}

sealed class Destination {
    data class File(val path: String, val size: Long?) : Destination()
    data class Netcat(val port: Long) : Destination()
}

enum class InfoLevel {
    Q,
    V,
    VV,
    VVV
}
