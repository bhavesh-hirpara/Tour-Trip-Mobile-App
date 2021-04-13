package com.tripkipedia.apputils

import android.text.TextUtils
import android.util.Log
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
object Debug {

    val DEBUG = true
    val DEBUG_TOAST = true
    val DEBUG_ADVERTISEMENT = true
    val SANDBOX_API_URL = false
    private val MAX_LOG_LENGTH = 4000

    fun e(tag: String, msg: String?) {
        var finalTag = tag
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                finalTag = "unknown"

            //            Log.e(tag, msg);
            log(msg, object : LogCB {
                override fun log(message: String) {
                    Log.e(finalTag, message)
                }
            })
        }
    }

    fun e(msg: String?) {
        var finalTag = "TAG"
        if (DEBUG) {
            if (TextUtils.isEmpty(finalTag))
                finalTag = "unknown"

            //            Log.e(tag, msg);
            log(msg, object : LogCB {
                override fun log(message: String) {
                    Log.e(finalTag, message)
                }
            })
        }
    }

    fun i(tag: String, msg: String) {
        var finalTag = tag
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                finalTag = "unknown"
            //            Log.i(tag, msg);

            log(msg, object : LogCB {
                override fun log(message: String) {
                    Log.i(finalTag, message)
                }
            })
        }
    }

    fun w(tag: String, msg: String) {
        var finalTag = tag
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                finalTag = "unknown"
            //            Log.w(tag, msg);

            log(msg, object : LogCB {
                override fun log(message: String) {
                    Log.w(finalTag, message)
                }
            })
        }
    }

    fun d(tag: String, msg: String) {
        var finalTag = tag
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                finalTag = "unknown"
            //            Log.d(tag, msg);
            log(msg, object : LogCB {
                override fun log(message: String) {
                    Log.d(finalTag, message)
                }
            })
        }
    }

    fun v(tag: String, msg: String) {
        var finalTag = tag
        if (DEBUG) {
            if (TextUtils.isEmpty(tag))
                finalTag = "unknown"
            //            Log.v(tag, msg);
            log(msg, object : LogCB {
                override fun log(message: String) {
                    Log.v(finalTag, message)
                }
            })
        }
    }

    private fun log(message: String?, callback: LogCB) {
        if (message == null) {
            callback.log("null")
            return
        }
        // Split by line, then ensure each line can fit into Log's maximum length.
        var i = 0
        val length = message.length
        while (i < length) {
            var newline = message.indexOf('\n', i)
            newline = if (newline != -1) newline else length
            do {
                val end = Math.min(newline, i + MAX_LOG_LENGTH)
                callback.log(message.substring(i, end))
                i = end
            } while (i < newline)
            i++
        }
    }

    private fun log(message: String?, throwable: Throwable?, callback: LogCB) {
        if (throwable == null) {
            log(message, callback)
            return
        }
        if (message != null) {
            log(message + "\n" + Log.getStackTraceString(throwable), callback)
        } else {
            log(Log.getStackTraceString(throwable), callback)
        }
    }

    private interface LogCB {
        fun log(message: String)
    }
}
