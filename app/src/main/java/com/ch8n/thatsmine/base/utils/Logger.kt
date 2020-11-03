package com.ch8n.thatsmine.base.utils

import android.R.attr
import android.os.Build
import android.util.Log
import timber.log.Timber


/***
 * todo
 *  1. make it release tree cleaner
 *  2. add crash reporting
 *  3. add gradle field to check debug/release status
 *  4. make logger and debug tools into seprate package
 */
object ReleaseTree : Timber.Tree() {

    private const val MAX_LOG_LENGTH = 4000

    override fun log(priority: Int, tag: String?, message: String, error: Throwable?) {
        if (isLoggable(tag, priority)) {

            // Message is short enough, doesn't need to be broken into chunks
            if (message.length < MAX_LOG_LENGTH) {
                if (priority == Log.ASSERT) {
                    Timber.tag(tag).wtf(message);
                } else {
                    Log.println(priority, tag, message);
                }
                return
            }

            // Split by line, then ensure each line can fit into Log's max length
            var i = 0
            val length: Int = message.length
            while (i < length) {
                var newline: Int = message.indexOf('\n', i)
                newline = if (newline != -1) newline else length
                do {
                    val end = Math.min(newline, i + MAX_LOG_LENGTH)
                    val part: String = message.substring(i, end)
                    if (attr.priority == Log.ASSERT) {
                        Timber.tag(tag).wtf(part)
                    } else {
                        Log.println(priority, tag, part)
                    }
                    i = end
                } while (i < newline)
                i++
            }


            // log your crash to your favourite
            // Sending crash report to Firebase CrashAnalytics

            // FirebaseCrash.report(message);
            // FirebaseCrash.report(new Exception(message));
        }
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        // Don't log VERBOSE, DEBUG and INFO
        // Log only ERROR, WARN and WTF
        return !(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO)
    }


}


/***
 * todo
 *  1. make it release tree cleaner
 *  2. add crash reporting
 *  3. add gradle field to check debug/release status
 *  4. make logger and debug tools into seprate package
 */
class AdvanceDebugTree : Timber.DebugTree() {

    private fun forceIncreasePriority(priority: Int): Int {
        return when (priority) {
            Log.VERBOSE -> Log.ERROR
            Log.DEBUG -> Log.ERROR
            Log.INFO -> Log.ERROR
            else -> priority
        }
    }

    override fun log(priority: Int, tag: String?, message: String, error: Throwable?) {
        // Workaround for devices that doesn't show lower priority logs
        val updatePriority = when {
            Build.MANUFACTURER.equals("HUAWEI", true) -> forceIncreasePriority(priority)
            Build.MANUFACTURER.equals("samsung", true) -> forceIncreasePriority(priority)
            else -> priority
        }
        super.log(updatePriority, tag, message, error);
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        // Add log statements line number to the log
        return super.createStackElementTag(element).toString() + " - " + element.lineNumber
    }
}