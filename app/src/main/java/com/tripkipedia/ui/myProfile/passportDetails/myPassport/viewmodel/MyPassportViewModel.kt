package com.tripkipedia.ui.myProfile.passportDetails.myPassport.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.tripkipedia.R
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.ActivityMyPassportBinding
import com.tripkipedia.databinding.DialogDateTimePickerBinding
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.myProfile.passportDetails.myPassport.faceRecognization.view.FaceRecognizationActivity
import com.tripkipedia.ui.myProfile.passportDetails.view.PassportDetailsActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MyPassportViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityMyPassportBinding
    lateinit var mContext: Context


    fun setBinder(binder: ActivityMyPassportBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isBackShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("My Passport")
        binder.topbar.topBarClickListener = SlideMenuClickListener()
        init()

    }

    fun init() {

        binder.edtIssueCountry.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                CheckEditextEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                CheckEditextEmpty()
            }
        })
        binder.edtPassport.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                CheckEditextEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                CheckEditextEmpty()
            }
        })
        binder.tvExpireDate.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                CheckEditextEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                CheckEditextEmpty()
            }
        })
        binder.tvIssueDate.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                CheckEditextEmpty()
            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                CheckEditextEmpty()
            }
        })


    }

    inner class ViewClickHandler {
        fun onAddClick() {

        }

        fun onConfigureClick() {

        }


        fun onNextClick() {
            if (binder.edtIssueCountry.text!!.length == 0 || binder.edtPassport.text!!.length == 0 || binder.tvExpireDate.text!!.length == 0 || binder.tvIssueDate.text!!.length == 0) {

            } else {

                binder.imgAdd.setImageResource(R.drawable.ic_current)
                binder.tv1.visibility = View.GONE
                binder.imgConfigure.setImageResource(R.drawable.ic_current_blank)
                binder.tv2.setTextColor(ContextCompat.getColor(mContext, R.color.purple))

                binder.btnNext.visibility = View.GONE
                binder.llAddPassport.visibility = View.GONE
                binder.llConfigurePasscode.visibility = View.VISIBLE

            }
        }

        fun onNextInnerClick() {
            val i = Intent(mContext, FaceRecognizationActivity::class.java)
            (mContext as Activity).startActivity(i)
            (mContext as Activity).finish()

        }

        fun onIssueDateClick(v: View) {
            showIssueDatePickerDialog(v.context)
        }

        fun onExpireDateClick(v: View) {
            showExpireDatePickerDialog(v.context)
        }

    }

    inner class SlideMenuClickListener : TopBarClickListener {
        override fun onTopBarClickListener(view: View?, value: String?) {
            Utils.hideKeyBoard(getContext(), view!!)
            if (value.equals(getLabelText(R.string.back))) {
                try {
                    (mContext as Activity).finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }


    fun CheckEditextEmpty() {
        if (binder.edtIssueCountry.text!!.length == 0 || binder.edtPassport.text!!.length == 0 || binder.tvExpireDate.text!!.length == 0 || binder.tvIssueDate.text!!.length == 0) {
            binder.btnNext.setBackgroundResource(R.mipmap.button_next_unfilled)
        } else {
            binder.btnNext.setBackgroundResource(R.mipmap.btn_next_filled)
        }
    }

    var dialogDateTimePickerBinding: DialogDateTimePickerBinding? = null
    lateinit var dialogDatePicker: androidx.appcompat.app.AlertDialog
    fun showIssueDatePickerDialog(mContext: Context) {


        val pd: androidx.appcompat.app.AlertDialog.Builder =
            androidx.appcompat.app.AlertDialog.Builder(
                (mContext as Activity),
                R.style.MyAlertDialogStyle
            )
        dialogDateTimePickerBinding = DataBindingUtil.inflate<DialogDateTimePickerBinding>(
            LayoutInflater.from(mContext), R.layout.dialog_date_time_picker, null, false
        )
        pd.setView(dialogDateTimePickerBinding!!.root)
        dialogDatePicker = pd.create()
        dialogDateTimePickerBinding!!.tvSubmit.setOnClickListener {

            binder.tvIssueDate.setText(dialogDateTimePickerBinding!!.datePicker1.dayOfMonth.toString() + " / " + dialogDateTimePickerBinding!!.datePicker1.month.inc() + " / " + dialogDateTimePickerBinding!!.datePicker1.year.toString())



            dialogDatePicker.dismiss()
        }
        dialogDateTimePickerBinding!!.tvCancel.setOnClickListener {
            dialogDatePicker.dismiss()
        }




        dialogDatePicker.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        try {
            dialogDatePicker.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun showExpireDatePickerDialog(mContext: Context) {


        val pd: androidx.appcompat.app.AlertDialog.Builder =
            androidx.appcompat.app.AlertDialog.Builder(
                (mContext as Activity),
                R.style.MyAlertDialogStyle
            )
        dialogDateTimePickerBinding = DataBindingUtil.inflate<DialogDateTimePickerBinding>(
            LayoutInflater.from(mContext), R.layout.dialog_date_time_picker, null, false
        )
        pd.setView(dialogDateTimePickerBinding!!.root)
        dialogDatePicker = pd.create()
        dialogDateTimePickerBinding!!.tvSubmit.setOnClickListener {

            binder.tvExpireDate.setText(dialogDateTimePickerBinding!!.datePicker1.dayOfMonth.toString() + " / " + dialogDateTimePickerBinding!!.datePicker1.month.inc() + " / " + dialogDateTimePickerBinding!!.datePicker1.year.toString())



            dialogDatePicker.dismiss()
        }
        dialogDateTimePickerBinding!!.tvCancel.setOnClickListener {
            dialogDatePicker.dismiss()
        }



        dialogDatePicker.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        try {
            dialogDatePicker.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}


