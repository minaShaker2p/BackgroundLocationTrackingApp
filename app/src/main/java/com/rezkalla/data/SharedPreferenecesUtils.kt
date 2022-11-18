package com.rezkalla.data

import android.content.SharedPreferences

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

val SharedPreferences.delegates get() = SharedPreferenceDelegates(this)

class SharedPreferenceDelegates(private val prefs: SharedPreferences) {
    fun boolean(default: Boolean = false, key: String? = null) =
        create(default, key, prefs::getBoolean, prefs.edit()::putBoolean)

    fun float(default: Float = 0.0F, key: String? = null) =
        create(default, key, prefs::getFloat, prefs.edit()::putFloat)

    fun int(default: Int = 0, key: String? = null) =
        create(default, key, prefs::getInt, prefs.edit()::putInt)

    fun string(default: String = "", key: String? = null) =
        create(default, key, { k, d -> prefs.getString(k, d) as String }, prefs.edit()::putString)

    fun long(default: Long = 0L, key: String? = null) =
        create(default, key, prefs::getLong, prefs.edit()::putLong)


}

fun <T : Any> create(
    default: T,
    key: String?,
    getter: (key: String, default: T) -> T,
    setter: (key: String, value: T) -> SharedPreferences.Editor
) =
    object : ReadWriteProperty<Any, T> {
        private fun key(property: KProperty<*>) = key ?: property.name
        override fun getValue(thisRef: Any, property: KProperty<*>): T =
            getter(key(property), default)

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) =
            setter(key(property), value).apply()
    }



