package com.example.aac.databinding

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.example.aac.BR


class User2 : BaseObservable{
    @Bindable
    var name: String = ""
    set(value) {
        field = value
        notifyChange()
    }

    @Bindable
    var password: String = ""
     set(value) {
         field = value
         notifyPropertyChanged(BR.password)
     }
    constructor(name: String, password: String) {
        this.name = name
        this.password = password
    }

//    var nickName :ObservableField<String> = ObservableField("")




}