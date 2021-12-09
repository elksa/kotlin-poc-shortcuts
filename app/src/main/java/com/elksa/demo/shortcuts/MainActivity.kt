package com.elksa.demo.shortcuts

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.elksa.demo.shortcuts.core.CoreActivity

private const val ID_SHORTCUT_WEB = "idDynamicWeb"
private const val ID_SHORTCUT_2 = "idDynamic2"
private const val ID_SHORTCUT_1 = "idDynamic1"

class MainActivity : AppCompatActivity() {

    private lateinit var txtShortcutsInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtShortcutsInfo = findViewById(R.id.txt_shortcuts_info)

        updateDynamicShortcutsInfo(this)
    }

    private fun addShortcutWeb(context: Context) {
        val shortcutWeb = ShortcutInfoCompat .Builder(context, ID_SHORTCUT_WEB)
            .setShortLabel(getString(R.string.title_dynamic_web))
            .setLongLabel(getString(R.string.desc_dynamic_web))
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_baseline_language_24))
            .setIntent(Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    "https://developer.android.com/guide/topics/ui/shortcuts/creating-shortcuts"
                )))
            .setRank(0)
            .build()
        ShortcutManagerCompat.pushDynamicShortcut(context, shortcutWeb)
    }

    private fun addShortcut2(context: Context) {
        val intent2 = Intent(this, DynamicShortcutActivity::class.java).apply {
            action = Intent.ACTION_VIEW
        }
        val shortcut2 = ShortcutInfoCompat.Builder(context, ID_SHORTCUT_2)
            .setShortLabel(getString(R.string.title_dynamic2))
            .setLongLabel(getString(R.string.desc_dynamic2))
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_baseline_analytics_24))
            .setIntent(intent2)
            .setRank(2)
            .build()

        ShortcutManagerCompat.pushDynamicShortcut(context, shortcut2)
    }

    private fun addShortcut1(context: Context) {
        val intent = Intent(this, ShortcutActivity0::class.java).apply {
            action = Intent.ACTION_VIEW
        }
        val shortcut = ShortcutInfoCompat.Builder(context, ID_SHORTCUT_1)
            .setShortLabel(getString(R.string.title_dynamic1))
            .setLongLabel(getString(R.string.desc_dynamic1))
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_baseline_filter_1_24))
            .setIntent(intent)
            .setRank(1)
            .build()

        ShortcutManagerCompat.pushDynamicShortcut(context, shortcut)
    }

    private fun updateDynamicShortcutsInfo(context: Context) {
        val shortcuts = ShortcutManagerCompat.getDynamicShortcuts(context)
        var info = ""
        shortcuts.forEach {
            info += "${String.format(getString(
                R.string.format_shortcut_info), 
                it.shortLabel,
                it.rank
            )}\n"
        }

        info.removeSuffix("\n")

        txtShortcutsInfo.text = info
    }

    fun onClickAdd(view: View) {
        view.context.run {
            addShortcutWeb(this)
            addShortcut1(this)
            addShortcut2(this)
            updateDynamicShortcutsInfo(this)
        }
    }

    fun onClickAddWeb(view: View) {
        addShortcutWeb(view.context)
    }

    fun onClickAdd2(view: View) {
        addShortcut2(view.context)
    }

    fun onClickRemoveWeb(view: View) {
        ShortcutManagerCompat.removeDynamicShortcuts(view.context, listOf(ID_SHORTCUT_WEB))
    }

    fun onClickRemove2(view: View) {
        ShortcutManagerCompat.removeDynamicShortcuts(view.context, listOf(ID_SHORTCUT_2))
    }

    fun onClickDisable2(view: View) {
        ShortcutManagerCompat.disableShortcuts(view.context, listOf(ID_SHORTCUT_2), getString(R.string.disabled_shortcut2))
    }

    fun onClickRemoveAll(view: View) {
        view.context.run {
            ShortcutManagerCompat.removeAllDynamicShortcuts(this)
            updateDynamicShortcutsInfo(this)
        }
    }

    fun onClickGoLibrary(view: View) {
        startActivity(Intent(view.context, CoreActivity::class.java))
    }
}