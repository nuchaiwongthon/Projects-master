package project.bc.nu.projects


import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class PreCachingLinearLayoutManeger(context: Context) : LinearLayoutManager(context) {

    private val DEFAULT_EXTRA_LAYOUT_SPACE = 600
    var extraLayoutSpace = -1

    override fun getExtraLayoutSpace(state: RecyclerView.State?): Int {
        if (extraLayoutSpace > 0) {
            return extraLayoutSpace
        }
        return DEFAULT_EXTRA_LAYOUT_SPACE
    }
}