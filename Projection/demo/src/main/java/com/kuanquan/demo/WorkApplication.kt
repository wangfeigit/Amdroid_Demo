package com.kuanquan.demo

import android.app.Activity
import android.app.Application
import android.os.Bundle

class WorkApplication : Application() {

    companion object {
        lateinit var instance: WorkApplication

        @kotlin.jvm.JvmField
        var currentActivity: Activity? = null // 应用内最新打开的 Activity
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity) {
            }

            override fun onActivityResumed(activity: Activity) {
                currentActivity = activity
            }

            override fun onActivityStarted(activity: Activity) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                if (currentActivity?.javaClass?.name.equals(activity.javaClass.name)) {
                    currentActivity = null
                }
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityStopped(activity: Activity) {
            }

            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            }
        })
    }
}
