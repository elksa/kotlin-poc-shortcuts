package com.elksa.demo.shortcuts.core

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat

private const val ID_SHORTCUT_LIBRARY = "idDynamicLibrary2"

class CoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_core)
    }

    fun onClickAdd(view: View) {
        val shortcut = ShortcutInfoCompat.Builder(this, ID_SHORTCUT_LIBRARY)
            .setShortLabel(getString(R.string.title_shortcut_library))
            .setLongLabel(getString(R.string.desc_dynamic_library))
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_baseline_adb_24))
            .setIntent(
                Intent(this, CoreActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                }
            )
            .build()

        ShortcutManagerCompat.pushDynamicShortcut(this, shortcut)
    }
}