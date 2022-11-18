package com.rezkalla.data

import android.content.SharedPreferences


class SettingRepository(preferences: SharedPreferences) {
    var isEnabled: Boolean by preferences.delegates.boolean(true)
    var nickName: String by preferences.delegates.string("UNKNOWN", "Name")
    var throttle: Float by preferences.delegates.float(0.0f)
}