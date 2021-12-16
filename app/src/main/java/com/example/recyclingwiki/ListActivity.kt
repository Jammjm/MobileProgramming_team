package com.example.recyclingwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.item.*

class ListActivity : AppCompatActivity() {
         val item_List : MutableList<items_list> = arrayListOf<items_list>(

            /*
            ** CODE FOR RECYCLING EXPLANATION
            * 1 - PAPER
            * 2 - CAN
            * 3 - GLASS
            * 4 - PLASTIC
            * 5 - VINYL
            * 6 - STYROFOAM

             */
            items_list("Price tag", "paper", 1),
            items_list("Pizza box", "paper", 1),
             items_list("poster", "paper", 1),
             items_list("Chicken box", "paper", 1),
             items_list("Book", "paper", 1),
             items_list("Paper tube", "paper", 1),
             items_list("Paper box", "paper", 1),
             items_list("Phone book", "paper", 1),
             items_list("Leaflet", "paper", 1),
             items_list("Magazine", "paper", 1),
             items_list("Newspaper", "paper", 1),
             items_list("Diary", "paper", 1),
             items_list("Envelope", "paper", 1),
             items_list("Rice sack", "paper", 1),
             items_list("Dictionary", "paper", 1),
             items_list("Name card", "paper", 1),
             items_list("Wrap tube", "paper", 1),
             items_list("Flyer", "paper", 1),
             items_list("Cardboard", "paper", 1),
             items_list("Price tag", "paper", 1),
             items_list("Milk carton", "paper_pack", 1),
             items_list("Carton", "paper_pack", 1),
             items_list("Spray", "can", 2),
             items_list("Butane gas", "can", 2),
             items_list("Feed can", "can", 2),
             items_list("Tool (steel)", "can", 2),
             items_list("Frying pan", "can", 2),
             items_list("Saw", "can", 2),
             items_list("Cup (steel)", "can", 2),
             items_list("Can opener", "can", 2),
             items_list("Iron plate", "can", 2),
             items_list("Kettle (steel, aluminum) ", "can", 2),
             items_list("Ashtray (metal)", "can", 2),
             items_list("Cloth drying rack (steel)", "can", 2),
             items_list("Bottle cap (steel, aluminum)", "can", 2),
             items_list("Hanger (wire)", "can", 2),
             items_list("Weightlifting", "can", 2),
             items_list("Dustpan (metal)", "can", 2),
             items_list("Infant formula can", "can", 2),
             items_list("Pond", "can", 2), items_list("Spray", "can", 2),
             items_list("Beer can", "can", 2),
             items_list("Ax", "can", 2),
             items_list("Bowl (metal)", "can", 2),
             items_list("Dish (metal)", "can", 2),
             items_list("Ladle", "can", 2),
             items_list("Glass bottle", "glass", 3),
             items_list("Ladle (plastic)", "plastic", 4),
             items_list("PET bottle", "plastic", 4),
             items_list("Ketchup container", "plastic", 4),
             items_list("Cup (plastic)", "plastic", 4),
             items_list("Cap (plastic)", "plastic", 4),
             items_list("Toothpaste container", "plastic", 4),
             items_list("Feeding bottle", "plastic", 4),
             items_list("Cooking oil container", "plastic", 4),
             items_list("Dustpan (plastic)", "plastic", 4),
             items_list("Shampoo container", "plastic", 4),
             items_list("Video tape", "plastic", 4),
             items_list("Sprayer", "plastic", 4),
             items_list("Straw", "plastic", 4),
             items_list("Megaphone", "plastic", 4),
             items_list("Recoder", "plastic", 4),
             items_list("Cutting board (plastic)", "plastic", 4),
             items_list("Dish (plastic)", "plastic", 4),
             items_list("Bowl (plastic)", "plastic", 4),
             items_list("Plastic bag", "vinyl", 5),
             items_list("Air cap", "vinyl", 5),

             )





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val list_item_Adapter = MainListAdapter(this, item_List)
        mainListView.adapter = list_item_Adapter

       val layout = LinearLayoutManager(this)
       mainListView.layoutManager = layout
       mainListView.setHasFixedSize(true)



        search_filter.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int, before: Int, count: Int) {

                list_item_Adapter.filter?.filter(s)
                //list_item_Adapter.notifyDataSetChanged();

            }
        })


    }
}