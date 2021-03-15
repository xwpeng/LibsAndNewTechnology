package com.example.aac.databinding

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable

import com.example.aac.R
import kotlinx.android.synthetic.main.activity_databinding.*
import java.lang.ref.WeakReference
import kotlin.math.log

/***
 * 存在以下疑问：
 * 1.普通的类没有正向绑定，反倒是有反向绑定
 * 2.kt方式的静态方法不知道如何在布局文件中引用
 * 3.绑定事件xml中的方法不知如何写
 * 4.两个相同类型变量绑定问题:variable的name不同可以区分
 */
class DataBindingActivity : AppCompatActivity(){
private lateinit var mUser: User
private lateinit var mUser2: User2
private lateinit var dataBinding: ActivityDatabindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView<ActivityDatabindingBinding>(this@DataBindingActivity, R.layout.activity_databinding)
        mUser = User("xwpeng", "123456")
        mUser2 = User2("xwpeng2", "pw2")
        dataBinding.userInfo = mUser
        dataBinding.userInfo2 = mUser2
        dataBinding.listener = Listener(this@DataBindingActivity)
        mUser.name = "change xx"
        Thread{
            Thread.sleep(30 * 1000)
            mUser.password = "pwd xx"
            Log.e("xwpeng12", mUser.password)
        }.start()
        Thread{
            Thread.sleep(30 * 1000)
            mUser2.name = "name after 30s"
            mUser2.password = "111"
            Log.e("xwpeng12", mUser2.name)
        }.start()
        mUser2.addOnPropertyChangedCallback(Callback())
//        dataBinding.tvName.text = "45678"
    }

    class Listener(dataBindingActivity: DataBindingActivity){
        val dataBindingActivity = WeakReference<DataBindingActivity>(dataBindingActivity).get()

        fun onNameCLick(name:String){
               Log.e("xwpeng12", "onNameCLick: ${name}" )
        }
        fun afterPwqChange(editable : Editable){
            Log.e("xwpeng12", "afterPwqChange: ${editable.toString()}")
            Log.e("xwpeng12", "afterPwqChange: ${dataBindingActivity?.mUser?.password}")

        }
    }

        class Callback : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
               Log.e("xwpeng12", "${sender}--------${propertyId}")
            }
        }
}