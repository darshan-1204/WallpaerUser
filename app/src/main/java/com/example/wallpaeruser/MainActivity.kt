package com.example.wallpaeruser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wallpaeruser.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainActivity : AppCompatActivity() {

    lateinit var reference: DatabaseReference
    private val TAG = "MainActivity"
    var modelList = ArrayList<WallpaperModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        reference = FirebaseDatabase.getInstance().reference

        reference.root.child("Images").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                modelList.clear()

                for (snap in snapshot.children){
                    val model = snap.getValue(WallpaperModel::class.java)
                    modelList.add(model!!)
                }

                for (data in modelList){
                    Log.e(TAG, "onDataChange: ======"+data.key )
                    Log.e(TAG, "onDataChange: ======"+data.img )
                    Log.e(TAG, "onDataChange: ==================" )
                }

                binding.rcvWallpaper.layoutManager = GridLayoutManager(this@MainActivity,3)
                binding.rcvWallpaper.adapter = WallpaperAdapter(modelList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}