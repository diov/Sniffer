package io.github.diov.sniffer

import android.app.Application
import io.github.diov.sniffer.misc.Core

/**
 * Sniffer
 *
 * Created by Dio_V on 2019-06-18.
 * Copyright © 2019 diov.github.io. All rights reserved.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Core.init(this)
    }
}
