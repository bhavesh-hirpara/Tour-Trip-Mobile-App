package com.tripkipedia.ui.choose_interest.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Binder
import android.view.View
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityChooseInterestBinding
import com.tripkipedia.interfaces.TopBarClickListener
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class ChooseInterestViewModel(application: Application) : BaseViewModel(application) {
 private lateinit var binder: ActivityChooseInterestBinding
    private lateinit var mContext: Context


    fun setBinder(binding: ActivityChooseInterestBinding) {
        binder=binding
        mContext=binding.root.context
        init()

    }


     private fun init() {
         binder.viewClickHandler = ViewClickHandler()
         binder.toopbar.topBarClickListener = ViewBackClickListener()
         binder.toopbar.tvTitle.text=mContext.resources.getString(R.string.title)


     }

    inner class ViewClickHandler {
         /*fun onVerify(view: View){
             try {
                 val i = Intent(mContext, ChooseInterestActivity::class.java)
                 (mContext as Activity).startActivity(i)

             } catch (e: Exception) {
                 e.printStackTrace()
             }

         }*/

     }
     inner class ViewBackClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                (mContext as Activity).finish()
            }
        }
    }
}