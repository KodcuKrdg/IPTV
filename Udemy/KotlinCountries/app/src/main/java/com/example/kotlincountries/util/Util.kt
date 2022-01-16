package com.example.kotlincountries.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlincountries.R

fun ImageView.downloadFromUrl(url: String?,progressDrawable: CircularProgressDrawable){
    val options = RequestOptions() //Görsel inene kadar ne gösterilecek
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round) // Hata çıkarsa default resimlerden birini göstericeğiz

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}
        //Resimler inene kadar gösterilecek bar
fun placeHodlerProgressBar(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f //Genişlik
        centerRadius = 40f//YarıÇap
        start()
    }
}
//@BindingAdapter() -> XML de fonsiyonu çalıştırmaya imkan sağlar
@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView,url: String?){
    view.downloadFromUrl(url, placeHodlerProgressBar(view.context))
}