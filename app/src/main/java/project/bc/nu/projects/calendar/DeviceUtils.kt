package project.bc.nu.projects


import android.R.attr.x
import android.view.Display
import android.content.Context.WINDOW_SERVICE
import android.view.WindowManager
import android.R.attr.y
import android.content.Context
import android.graphics.Point


class DeviceUtils() {

    private lateinit var context :Context

    constructor(context: Context) : this() {
        this.context = context
    }

    fun getScreenHeight(): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.y
    }


    fun getScreenWidth(): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size.x
    }

}