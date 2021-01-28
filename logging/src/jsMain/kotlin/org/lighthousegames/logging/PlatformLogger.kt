package org.lighthousegames.logging

import kotlin.js.Date


actual class PlatformLogger actual constructor(logLevel: LogLevelController) : Logger,
    LogLevelController by logLevel {

    actual override fun verbose(tag: String?, msg: String) {
        console.log(preface("V", tag), msg)
    }

    actual override fun debug(tag: String?, msg: String) {
        console.log(preface("D", tag), msg)
    }

    actual override fun info(tag: String?, msg: String) {
        console.info(preface("I", tag), msg)
    }

    actual override fun warn(tag: String?, msg: String, t: Throwable?) {
        console.warn(preface("W", tag), msg, t)
    }

    actual override fun error(tag: String?, msg: String, t: Throwable?) {
        console.error(preface("E", tag), msg, t)
    }

    actual fun createTag(): String {
        return ""
    }

    private fun preface(level: String, tag: String?): String {
        val d = Date()
        val timestamp =
            "${d.getMonth() + 1}/${d.getDate()} ${d2(d.getHours())}:${d2(d.getMinutes())}:${d2(d.getSeconds())}.${
                d3(d.getMilliseconds())
            }"
        val str = if (tag == null) "$level:" else "$level/$tag:"
        return "$timestamp $str"
    }

    private fun d2(i: Int): String {
        return if (i < 10) "0$i" else i.toString()
    }

    private fun d3(i: Int): String {
        return if (i < 100) ("0" + if (i < 10) "0" else "") + i else i.toString()
    }
}