package yong.aop.aop.tworecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class MainAdapter(val locationList: ArrayList<HashMap<String, String>>): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    var byDates = locationList.groupBy { it["time"] }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {

        val sdf = SimpleDateFormat("MM/dd/yyyy")
        val dateList = byDates.values.toMutableList()
        holder.date?.text = sdf.format(dateList[position][0].get("time")?.toLong())
        // Create vertical Layout Manager
        holder.rv?.layoutManager =
            LinearLayoutManager(holder.rv.context, LinearLayoutManager.VERTICAL, false)
        // Access RecyclerView Adapter and load the data
        var adapter = MainLocationAdapter(dateList[position] as ArrayList<HashMap<String, String>>)
        holder.rv?.adapter = adapter
        holder.date.setOnClickListener {
            itemClickListener.onClick(it, position,holder)
        }

    }
    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int, holder: ViewHolder)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_dates, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return byDates.count()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private var mClick = true
        var date = itemView.findViewById<TextView>(R.id.locationDate)
        var rv = itemView.findViewById<RecyclerView>(R.id.locationList)

//        fun onBind() {
//            itemView.setOnClickListener {
//                if(rv.isVisible){
//                    rv.setVisibility(View.GONE)
////                    itemView.findViewById<TextView>(R.id.textview).setText(date.toString())
//                    mClick = false
//                } else{
//                    rv.setVisibility(View.VISIBLE)
//                    mClick = true
//                }
////                date.toString()
//            }
////            date.setOnClickListener {
////                if(mClick){
////                    rv.setVisibility(View.GONE)
//////                    itemView.findViewById<TextView>(R.id.textview).setText(date.toString())
////                    mClick = false
////                } else{
////                    rv.setVisibility(View.VISIBLE)
////                    mClick = true
////                }
////            }
//        }
    }


}