package com.kasunthilina.ktfbintergration

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    var options:RequestOptions
    var mContext: Context
    var mData: List<Data>
    constructor(mContext: Context, mData: List<Data>)
    {
        this.mContext = mContext
        this.mData = mData

        options = RequestOptions().centerCrop().timeout(7500)
    }
   /* fun RecyclerViewAdapter(mContext: Context, mData: List<Data>) {
        this.mContext = mContext
        this.mData = mData

        options = RequestOptions().centerCrop().timeout(7500)
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view:View
        var inflater=LayoutInflater.from(mContext)
        view=inflater.inflate(R.layout.row_item,parent,false)

        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
       return mData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.setText(mData[position].getTitle())
        holder.tvAddress.setText(mData[position].getAddress())
        holder.tvPostCode.setText(mData[position].getPostcode())
        holder.tvPhoneNo.setText(mData[position].getPhoneNumber())
        //Setting up image using glide library
        Glide.with(mContext).load(mData[position].getImage()).apply(options)
            .into(holder.imgThumbnail)


      /*  holder.parentLayout.setOnClickListener(View.OnClickListener {
            val intent=Intent(mContext,Deta)
        })*/
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