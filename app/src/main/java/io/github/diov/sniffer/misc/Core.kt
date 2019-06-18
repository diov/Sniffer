package io.github.diov.sniffer.misc

import android.app.Application
import android.os.Build

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-18.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

object Core {

    lateinit var app: Application
    val deviceStorage by lazy { if (Build.VERSION.SDK_INT < 24) app else DeviceStorageApp(app) }

    fun init(app: Application) {
        this.app = app
    }
}
