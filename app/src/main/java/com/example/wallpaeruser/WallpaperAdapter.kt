package com.example.wallpaeruser

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class WallpaperAdapter(modelList: ArrayList<WallpaperModel>) :
    Adapter<WallpaperAdapter.ImageHolder>() {

    var modelList = modelList


    class ImageHolder(itemView: View) : ViewHolder(itemView) {
        var img = itemView.findViewById<ImageView>(R.id.imgWall)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        return ImageHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.img_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return modelList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        Glide.with(holder.itemView.context).load(modelList.get(position).img).into(holder.img)

        holder.itemView.setOnClickListener {

            var dialog = Dialog(holder.itemView.context)
            dialog.setContentView(R.layout.dialog_item)
            dialog.window?.setBackgroundDrawable(ColorDrawable(android.R.color.transparent))

            var imgD = dialog.findViewById<ImageView>(R.id.imgDialog)
            var btn = dialog.findViewById<Button>(R.id.btnDialog)


            Glide.with(holder.itemView.context).load(modelList.get(position).img).into(imgD)

            dialog.show()

        }
    }
}

