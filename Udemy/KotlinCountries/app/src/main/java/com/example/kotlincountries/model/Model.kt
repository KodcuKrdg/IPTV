package com.example.kotlincountries.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Country(
    @ColumnInfo(name = "name") //Ofline veritabanına kaydediyoruz
    @SerializedName("name") // API dan çekilecek verinin ismi ve değerini alta atadık
    val countryName : String?,

    @ColumnInfo(name = "region")
    @SerializedName("region")
    val countryRegion : String?,

    @ColumnInfo(name = "capital")
    @SerializedName("capital")
    val countryCapital : String?,

    @ColumnInfo(name = "currency")
    @SerializedName("currency")
    val countryCurrency : String?,

    @ColumnInfo(name = "language")
    @SerializedName("language")
    val countryLanguage : String?,

    @ColumnInfo(name = "flag")
    @SerializedName("flag")
    val imageUrl : String?){

    @PrimaryKey(autoGenerate = true) //otomatik artması
    var uuid: Int = 0

}