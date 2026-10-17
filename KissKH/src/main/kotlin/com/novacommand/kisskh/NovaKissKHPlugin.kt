package com.novacommand.kisskh

import android.content.Context
import com.lagradost.cloudstream3.plugins.CloudstreamPlugin
import com.lagradost.cloudstream3.plugins.Plugin

@CloudstreamPlugin
class NovaKissKHPlugin : Plugin() {
    override fun load(context: Context) {
        registerMainAPI(KissKH())
    }
}
