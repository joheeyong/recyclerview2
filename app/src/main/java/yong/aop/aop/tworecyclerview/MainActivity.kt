package yong.aop.aop.tworecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    var a=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Toast.makeText(applicationContext, "abc", Toast.LENGTH_SHORT).show()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://domeggook.com/ssl/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(RetrofitAPI::class.java)
        retrofitAPI.getData().enqueue(object : Callback<cat> {
            override fun onResponse(call: Call<cat>, response: Response<cat>) {
                if (response.isSuccessful) {
                    var dayArray = arrayOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

                    Log.d("TEST", response.body()!!.domeggook!!.items!!.item!!.a1!!)
                    val items: ArrayList<HashMap<String, String>> = ArrayList()
                    val time = System.currentTimeMillis()
                    // Load items into ArrayList
                    items.add(hashMapOf("name" to "Walmart", "address" to response.body()!!.domeggook!!.items!!.item!!.a1!!, "time" to time.toString()))
                    items.add(hashMapOf("name" to "CVS", "address" to "7250 Carson Blvd, Long Beach CA 90808, USA", "time" to time.toString()))
                    items.add(hashMapOf("name" to "Target", "address" to "7250 Carson Blvd, Long Beach CA 90808, USA", "time" to time.toString()))

                    val tomorrowTime = time + (1000 * 60 * 60 * 24 * 3)
                    items.add(hashMapOf("name" to "Whole Foods", "address" to "7250 Carson Blvd, Long Beach CA 90808, USA", "time" to tomorrowTime.toString()))
                    items.add(hashMapOf("name" to "Rite Aid", "address" to "7250 Carson Blvd, Long Beach CA 90808, USA", "time" to tomorrowTime.toString()))
                    items.add(hashMapOf("name" to "Publix", "address" to "7250 Carson Blvd, Long Beach CA 90808, USA", "time" to tomorrowTime.toString()))
                    // Bind items to RecyclerView
                    val rv = findViewById<RecyclerView>(R.id.locationDatesList)
                    val rvv = findViewById<RecyclerView>(R.id.locationList)
                    rv.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    // Access RecyclerView Adapter and load the data
                    val adapter = MainAdapter(items)
                    rv.adapter = adapter


                    adapter.setItemClickListener(object: MainAdapter.OnItemClickListener{
                        override fun onClick(v: View, position: Int, holder: MainAdapter.ViewHolder
                        ) {
                            if (holder.rv.isVisible){
                                holder.rv.setVisibility(View.GONE)
                            } else{
                                holder.rv.setVisibility(View.VISIBLE)
                            }
                            findViewById<TextView>(R.id.textview).text=holder.date.text.toString()

                        }

                    })
//                    val adapterr = MainLocationAdapter(items)
//                    rvv.adapter = adapterr
//                    adapterr.setItemClickListener(object: MainLocationAdapter.OnItemClickListener{
//                        override fun onClick(v: View, position: Int) {
//                            findViewById<TextView>(R.id.textview).text="holder.date.text.toString()"
//                        }
//
//                    })
//
//                    adapterr.setItemClickListener(object: MainLocationAdapter.OnItemClickListener{
//                        override fun onClick(v: View, position: Int,holder: MainLocationAdapter.ViewHolder) {
//
//                            Log.d("TEST", holder.locationAddress.toString())
//                        }
//                    })
                }
            }

            override fun onFailure(call: Call<cat>, t: Throwable) {
                t.printStackTrace()
                Log.d("TEST", "실패")
            }
        })

        createMockData()
    }

    private fun bindDataWithUi(itemData: ArrayList<HashMap<String, String>>) {
        // Create vertical Layout Manager
//        val rv = findViewById<RecyclerView>(R.id.locationDatesList)
//        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        // Access RecyclerView Adapter and load the data
//        val adapter = MainAdapter(itemData)
//        rv.adapter = adapter
//        adapter.setItemClickListener(object: MainAdapter.OnItemClickListener{
//            override fun onClick(v: View, position: Int) {
//                // 클릭 시 이벤트 작성
//
//                Log.d("TEST", dayArray[position])
////                Toast.makeText(Context,
////                    "${itemList[position].name}\n${itemList[position].number}",
////                    Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    private fun createMockData() {
        // Initialize test locations
    }

}
