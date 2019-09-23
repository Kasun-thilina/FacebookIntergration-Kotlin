package com.kasunthilina.ktfbintergration

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    lateinit var options:RequestOptions
    lateinit var mContext: Context
    lateinit var mData: List<Data>

    fun RecyclerViewAdapter(mContext: Context, mData: List<Data>) {
        this.mContext = mContext
        this.mData = mData

        options = RequestOptions().centerCrop().timeout(7500)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    class MyViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var tvTitle: TextView
        internal var tvDescription: TextView
        internal var tvAddress: TextView
        internal var tvPostCode: TextView
        internal var tvPhoneNo: TextView
        internal var imgThumbnail: ImageView
        internal var parentLayout: LinearLayout


        init {
            tvTitle = itemView.findViewById(R.id.tvHeading)
            tvDescription = itemView.findViewById(R.id.tvDescription)
            tvAddress = itemView.findViewById(R.id.tvAdress)
            tvPostCode = itemView.findViewById(R.id.tvPostCode)
            tvPhoneNo = itemView.findViewById(R.id.tvPhoneNumber)
            imgThumbnail = itemView.findViewById(R.id.imgThumbnail)
            parentLayout = itemView.findViewById(R.id.parentLayout)
        }
    }
}