package yong.aop.aop.tworecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MainLocationAdapter(val locationList: ArrayList<HashMap<String, String>>): RecyclerView.Adapter<MainLocationAdapter.ViewHolder>() {
    private lateinit var itemClickListenerr : OnItemClickListener
    override fun onBindViewHolder(holder: MainLocationAdapter.ViewHolder, position: Int) {
        val location = locationList[position]
        holder.locationName?.text = location.get("name")
        holder.locationAddress?.text = location.get("address")

        holder.locationAddress.setOnClickListener {
            itemClickListenerr.onClick(it, position)
        }
    }
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListenerr = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainLocationAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_locations, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return locationList.size
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val locationName = itemView.findViewById<TextView>(R.id.locationName)
        val locationAddress = itemView.findViewById<TextView>(R.id.locationAddress)


        fun bind() {

        }
    }

}