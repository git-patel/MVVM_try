package com.heaventrinity.mvvm_try

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.heaventrinity.mvvm_try.databinding.RawImgesBinding


class PhotoAdapter(private val context: Context, private var photos: List<Photo>) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    private var checkBoxCheckedListener: CheckBoxChecked? = null

    fun setCheckBoxCheckedListener(listener: CheckBoxChecked) {
        this.checkBoxCheckedListener = listener
    }

    fun updateDateSet(photos: List<Photo>) {
        this.photos = photos
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = RawImgesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    inner class PhotoViewHolder(private val binding: RawImgesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.checkbox.setOnCheckedChangeListener(null)
            Glide.with(context)
                .load(photo.url)
                .into(binding.photoImageView)

            binding.titleTextView.text = photo.title
            binding.checkbox.text = photo.id.toString()
            binding.checkbox.isChecked = photo.isSelected

            binding.checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                checkBoxCheckedListener?.onCheckBoxClick(photo.id.toString(), position,isChecked)
            }

            binding.executePendingBindings()
        }
    }

    interface CheckBoxChecked {
        fun onCheckBoxClick(id: String, pos: Int,checked : Boolean)
    }
}