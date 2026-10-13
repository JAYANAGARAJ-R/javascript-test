package com.nutritics.labelprinter

import android.app.Activity
import android.os.Bundle
import java.net.HttpURLConnection
import java.net.URL

// Scanner-validation fixture mirroring the printdrivermob scan. See EXPECTED_FINDINGS.md.
class MainActivity : Activity() {

    private lateinit var brother: Brother

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        brother = Brother(this)
        intent.getStringExtra(EXTRA_LABEL_URL)?.let { Thread { fetchLabel(it) }.start() }
    }

    // Destination comes straight from the launching intent and is requested as-is.
    private fun fetchLabel(labelUrl: String): ByteArray {
        val connection = URL(labelUrl).openConnection() as HttpURLConnection
        try {
            return connection.inputStream.use { it.readBytes() }
        } finally {
            connection.disconnect()
        }
    }

    companion object {
        const val EXTRA_LABEL_URL = "labelUrl"
    }
}
