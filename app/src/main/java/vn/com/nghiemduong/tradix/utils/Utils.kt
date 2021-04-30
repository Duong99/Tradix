package vn.com.nghiemduong.tradix.utils

import android.app.Activity
import android.content.Context
import kotlin.coroutines.CoroutineContext

const val REQUEST_CODE_SING_UP = 123
//const val REQUEST_CODE_SING_UP = 123

const val TAG = "TAG"

const val SKIP_TUTORIAL = "SKIP_TUTORIAL"
const val EMAIL = "EMAIL"
const val PASSWORD = "PASSWORD"
const val CHECK_LOGIN = "CHECK_LOGIN"


fun updateEmailPref(context: Context, emailValue: String) {

    val pref = context.getSharedPreferences(
        "TRADIX_SHARE_PREFERENCES",
        Activity.MODE_PRIVATE
    )

    pref.edit().putString(EMAIL, emailValue).apply()
}

fun getEmailPref(context: Context): String {
    val pref = context.getSharedPreferences(
        "TRADIX_SHARE_PREFERENCES",
        Activity.MODE_PRIVATE
    )

    return pref.getString(EMAIL, "").toString()
}

fun updatePasswordPref(context: Context, passwordValue: String) {

    val pref = context.getSharedPreferences(
        "TRADIX_SHARE_PREFERENCES",
        Activity.MODE_PRIVATE
    )

    pref.edit().putString(PASSWORD, passwordValue).apply()
}

fun getPasswordPref(context: Context): String {
    val pref = context.getSharedPreferences(
        "TRADIX_SHARE_PREFERENCES",
        Activity.MODE_PRIVATE
    )

    return pref.getString(PASSWORD, "").toString()
}

fun updateShipTutorialPref(context: Context, skipValue: Boolean) {

    val pref = context.getSharedPreferences(
        "TRADIX_SHARE_PREFERENCES",
        Activity.MODE_PRIVATE
    )

    pref.edit().putBoolean(SKIP_TUTORIAL, skipValue).apply()
}

fun getShipTutorialPref(context: Context): Boolean {
    val pref = context.getSharedPreferences(
        "TRADIX_SHARE_PREFERENCES",
        Activity.MODE_PRIVATE
    )

    return pref.getBoolean(SKIP_TUTORIAL, false)
}

fun updateCheckLoginPref(context: Context, loginValue: Boolean) {

    val pref = context.getSharedPreferences(
        "TRADIX_SHARE_PREFERENCES",
        Activity.MODE_PRIVATE
    )

    pref.edit().putBoolean(CHECK_LOGIN, loginValue).apply()
}

fun getCheckLoginPref(context: Context): Boolean {
    val pref = context.getSharedPreferences(
        "TRADIX_SHARE_PREFERENCES",
        Activity.MODE_PRIVATE
    )

    return pref.getBoolean(CHECK_LOGIN, false)
}






