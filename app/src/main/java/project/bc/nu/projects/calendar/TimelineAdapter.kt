package project.bc.nu.projects


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_time.view.*
import project.bc.nu.projects.SQLite.MyDB
import project.bc.nu.projects.calendar.CalendarMainActivity

import java.util.*

class TimelineAdapter(val data :ArrayList<MyDB.Timeline>) : RecyclerView.Adapter<TimelineAdapter.TimelineViewHolder>(){

    private lateinit var context :Context
    private lateinit var sq :MyDB





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
        R.layout.card_time, parent,false)



        this.context = parent.context

        this.sq = MyDB(context)


        return TimelineViewHolder(view)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TimelineViewHolder, position: Int) {

        val slot = data[position]


        val my = sq.getMyVEG()

        if(sq.isPass()){
            holder.title.text = slot.OBJECTIVE





            holder.message.text = calculateTime(slot.DATE,my)

            if(position == data.size - 1){
                holder.indicator.visibility = View.GONE
            }else {
                holder.indicator.visibility = View.VISIBLE
            }
        }



    }

    private fun calculateTime(time :Int,myVegatable: MyDB.MyVegatable) :String{
        var calendar :Calendar = Calendar.getInstance()
        calendar.set(myVegatable.YEAR,myVegatable.MONTH,myVegatable.DAY)
        calendar.add(Calendar.DAY_OF_YEAR,time)
        return convertDateToString(calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR))
    }



    class TimelineViewHolder(itemView :View) :RecyclerView.ViewHolder(itemView){

        var title = itemView.card_time_title
        var message = itemView.card_time_text
        var indicator = itemView.card_time_indicator

    }

    private fun convertDateToString(day: Int, month: Int, year: Int): String {
        return "" + day + " " +
                convertMonth_intToName(month + 1) + " " +(year)
    }

    private fun convertMonth_intToName(month: Int): String {
        when (month) {
            1 -> return "มกราคม"
            2 -> return "กุมภาพันธ์"
            3 -> return "มีนาคม"
            4 -> return "เมษายน"
            5 -> return "พฤษภาคม"
            6 -> return "มิถุนายน"
            7 -> return "กรกฎาคม"
            8 -> return "สิงหาคม"
            9 -> return "กันยายน"
            10 -> return "ตุลาคม"
            11 -> return "พฤศจิกายน"
            else -> return "ธันวาคม"
        }
    }


}