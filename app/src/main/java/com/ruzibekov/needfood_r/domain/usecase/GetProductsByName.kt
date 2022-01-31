package com.ruzibekov.needfood_r.domain.usecase

import com.ruzibekov.needfood_r.data.room.Product

class GetProductsByName {

    /* Klassning vazifasi:
    * Foydalanuvchi qidiruv tugmasini bosganida editText ichiga text kiritadi va bu text execute funksiyasi qabul qiladigan text
    * Manashu text bilan arrayList ichidagi obyekt(Product.name) birxilligi tekshiriladi.
    * Birxil bo'lganlari filterList: ArrayList ga add qilinib, return qilinadi */


    fun execute(list:ArrayList<Product>, text:String): ArrayList<Product>{

        var filterlist:ArrayList<Product> = ArrayList()
        for (i in 0..list.lastIndex){
            if (list[i].name.contains(text)){
                filterlist.add(list[i])
            }
        }

        return filterlist
    }

}