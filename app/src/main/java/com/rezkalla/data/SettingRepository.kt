package com.rezkalla.data

import android.content.SharedPreferences


class SettingRepository(preferences: SharedPreferences) {
    var isEnabled: Boolean by preferences
    var nickName: String by preferences
    var throttle: Float by preferences
}