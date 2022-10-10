package com.example.stressmeter

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import java.util.*


class HomeFragment : Fragment() {

    private var imageList = intArrayOf(0)
    private val valuesList = intArrayOf(6, 8, 14, 16, 5, 7, 13, 15, 2, 4, 10, 12, 1, 3, 9, 11)

    private lateinit var gridView: GridView
    private lateinit var changeButton: Button

    private var gridID = 0
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        gridView = view.findViewById<GridView>(R.id.gridview)
        changeButton = view.findViewById<Button>(R.id.button_home)

        imageList = changeImages()
        val imgAdapter = ImageAdapter(requireActivity(), imageList)

        gridView.adapter = imgAdapter

        gridView.setOnItemClickListener{ adapter, view, position, id ->
            val selectedImage = imageList[position]
            val intent = Intent(requireActivity(), ConfirmationActivity::class.java).putExtra("imagePath", selectedImage).putExtra("imageValue", valuesList[position])
            startActivity(intent)
        }

        changeButton.setOnClickListener(){
            imageList = changeImages()
            imgAdapter.setImageList(imageList)
            gridView.adapter = imgAdapter
        }

        return view
    }

    fun changeImages() : IntArray{
        var ret = intArrayOf(0)
        if(gridID == 0) ret = intArrayOf(R.drawable.psm_talking_on_phone2, R.drawable.psm_stressed_person, R.drawable.psm_stressed_person12, R.drawable.psm_lonely, R.drawable.psm_gambling4, R.drawable.psm_clutter3, R.drawable.psm_reading_in_bed2, R.drawable.psm_stressed_person4, R.drawable.psm_lake3, R.drawable.psm_cat, R.drawable.psm_puppy3, R.drawable.psm_neutral_person2, R.drawable.psm_beach3, R.drawable.psm_peaceful_person, R.drawable.psm_alarm_clock2, R.drawable.psm_sticky_notes2)
        else if(gridID == 1) ret = intArrayOf(R.drawable.psm_anxious, R.drawable.psm_hiking3, R.drawable.psm_stressed_person3, R.drawable.psm_lonely2, R.drawable.psm_dog_sleeping, R.drawable.psm_running4, R.drawable.psm_alarm_clock, R.drawable.psm_headache, R.drawable.psm_baby_sleeping, R.drawable.psm_puppy, R.drawable.psm_stressed_cat, R.drawable.psm_angry_face, R.drawable.psm_bar, R.drawable.psm_running3, R.drawable.psm_neutral_child, R.drawable.psm_headache2)
        else if(gridID == 2) ret = intArrayOf(R.drawable.psm_mountains11, R.drawable.psm_wine3, R.drawable.psm_barbed_wire2, R.drawable.psm_clutter, R.drawable.psm_blue_drop, R.drawable.psm_to_do_list, R.drawable.psm_stressed_person7, R.drawable.psm_stressed_person6, R.drawable.psm_yoga4, R.drawable.psm_bird3, R.drawable.psm_stressed_person8, R.drawable.psm_exam4, R.drawable.psm_kettle, R.drawable.psm_lawn_chairs3, R.drawable.psm_to_do_list3, R.drawable.psm_work4)

        gridID++
        gridID %= 3
        return ret
    }

    class ImageAdapter : BaseAdapter{

        private lateinit var inflater: LayoutInflater

        private lateinit var context: Context
        private var imagePaths: IntArray

        constructor(context: Context, mImgPathList: IntArray) : super() {
            this.context = context
            this.imagePaths = mImgPathList
            this.inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        override fun getCount(): Int {
            return imagePaths.size
        }

        override fun getItem(p0: Int): Any? {
            return imagePaths[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        fun setImageList(input: IntArray){
            imagePaths = input
        }

        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View? {
            var imageView = view
            if(imageView == null){
                imageView = inflater.inflate(R.layout.row_items, viewGroup, false)
            }

            var img = imageView?.findViewById<ImageView>(R.id.imgView)
            img?.setImageResource(imagePaths.get(position))

            return imageView
        }

    }
}