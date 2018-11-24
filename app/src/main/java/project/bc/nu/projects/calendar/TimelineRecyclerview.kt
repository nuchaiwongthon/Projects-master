package project.bc.nu.projects.calendar

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import project.bc.nu.projects.DeviceUtils
import project.bc.nu.projects.PreCachingLinearLayoutManeger
import project.bc.nu.projects.SQLite.MyDB
import project.bc.nu.projects.TimelineAdapter

class TimelineRecyclerview {

    private lateinit var adapter: TimelineAdapter

    private var setAdap = false

    fun useRecyclerView(context: Context, timeline_recyclerview: RecyclerView) {
        val timelineSQ = MyDB(context)

        //val act = context

        val layoutManager = PreCachingLinearLayoutManeger(context)
        layoutManager.apply {
            this.orientation = LinearLayoutManager.VERTICAL
            this.extraLayoutSpace = DeviceUtils(context).getScreenHeight()
            this.reverseLayout = false
        }



        timeline_recyclerview.apply {
            this.layoutManager = layoutManager
            this.setHasFixedSize(true)
            this.setItemViewCacheSize(10000)
            this.isDrawingCacheEnabled = true
            this.isNestedScrollingEnabled = true
            this.overScrollMode = View.OVER_SCROLL_NEVER
            this.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        }

        adapter = TimelineAdapter(timelineSQ.getTimeline(timelineSQ.getMyVEG().ID))
        timeline_recyclerview.adapter = adapter

        setAdap = true
    }

    fun reloadRecyclerview(){
        if(setAdap){
            adapter.notifyDataSetChanged()
        }
    }


}