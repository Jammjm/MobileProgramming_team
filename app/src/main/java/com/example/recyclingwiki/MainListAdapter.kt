package com.example.recyclingwiki

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class MainListAdapter(
    private val context: Context,
    private val item_list: MutableList<items_list>
) :
    RecyclerView.Adapter<MainListAdapter.ViewHolder>(),
    Filterable {
    var filteredList: MutableList<items_list>? = item_list

    //이너 클래스
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val Photo = view.PhotoImg
        val item_name = view.item_name

        fun bind(item: items_list, context: Context) {
            val resourceId =
                context.resources.getIdentifier(item.photo, "drawable", context.packageName)
            Photo.setImageResource(resourceId)
            item_name.text = item.item_name

            itemView.setOnClickListener{
                val code = item.code
                newactivity(context, code)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = filteredList?.get(position)
        if (current != null) {
            holder.bind(current, context)
        }


    }

    override fun getItemCount(): Int = filteredList!!.size

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                Log.d("jaemin", "$constraint")
                filteredList = if (charString.isEmpty()) {
                    item_list
                } else {
                    val filteringList = ArrayList<items_list>()
                    if (item_list != null) {
                        for (name in item_list) {
                            if (name.item_name.toLowerCase().contains(charString.toLowerCase())) {
                                filteringList.add(name);
                            }
                        }
                    }
                    filteringList
                }
                val filterResults = FilterResults()
                filterResults.values = filteredList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                filteredList = results.values as ArrayList<items_list>
                for (item in filteredList as ArrayList<items_list>) {
                    Log.d("jaemin", "${item.item_name}")
                }
                notifyDataSetChanged()
            }
        }
    }

    fun newactivity (context:Context, code:Int){
        var intent: Intent? = null
            when(code){
            1-> intent = Intent(context, paper::class.java)
            2-> intent = Intent(context, metalcan::class.java)
            3-> intent = Intent(context, glassbottle::class.java)
            4-> intent = Intent(context, pet::class.java)
            5-> intent = Intent(context, vinyl::class.java)
        }
        context.startActivity(intent)
    }




}



