package com.tripkipedia.ui.settings.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tripkipedia.R
import com.tripkipedia.apputils.Debug
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.*
import com.tripkipedia.ui.settings.accountSettings.view.AccountSettingsActivity
import com.tripkipedia.ui.settings.contactUs.view.ContactUsActivity
import com.tripkipedia.ui.settings.datamodel.CurrencyData
import com.tripkipedia.ui.settings.datamodel.LanguageData
import com.tripkipedia.ui.settings.notificationSettings.view.NotificationSettingsActivity
import com.tripkipedia.ui.settings.privacyPolicy.view.PrivacyPolicyActivity
import com.tripkipedia.ui.settings.termsAndConditions.view.TermsAndConditionActivity
import com.tripkipedia.ui.settings.utils.CurrencyAdapter
import com.tripkipedia.ui.settings.utils.LanguageAdapter
import java.util.ArrayList

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class SettingsViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivitySettingsBinding
    lateinit var mContext: Context


    fun setBinder(binder: ActivitySettingsBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isMenuShow = true
        binder.topbar.isTextShow = true
        binder.topbar.tvTitle.setText("Settings")
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()
        initDrawer(mContext)
        init()

    }

    fun init() {


    }

    inner class ViewClickHandler {
        fun onTermAndConditionClick() {

            val i = Intent(mContext, TermsAndConditionActivity::class.java)
            (mContext as Activity).startActivity(i)
        }

        fun onPrivacyPolicyClick() {

            val i = Intent(mContext, PrivacyPolicyActivity::class.java)
            (mContext as Activity).startActivity(i)
        }

        fun onAccountClick() {
            val i = Intent(mContext, AccountSettingsActivity::class.java)
            (mContext as Activity).startActivity(i)
        }

        fun onNotificationClick() {
            val i = Intent(mContext, NotificationSettingsActivity::class.java)
            (mContext as Activity).startActivity(i)
        }

        fun onContactUsClick() {
            val intent = Intent(mContext, ContactUsActivity::class.java)
            (mContext as Activity).startActivity(intent)
        }

        fun onCurrencyClick() {
            showCurrencyDialog()
        }

        fun onLanguageClick() {
            showLanguageDialog()
        }
    }

    var dialogCurrencySelectionBinding: DialogCurrencySelectionBinding? = null
    lateinit var dialogCurrency: BottomSheetDialog
    lateinit var currencyAdapter: CurrencyAdapter

    fun showCurrencyDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_currency_selection, null)
            dialogCurrencySelectionBinding = DataBindingUtil.bind(v)
            dialogCurrency = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogCurrency.setContentView(v)
            dialogCurrency.setCanceledOnTouchOutside(false)
            dialogCurrency.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            dialogCurrency.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            val list: ArrayList<CurrencyData> = ArrayList<CurrencyData>()
            val country: Array<String> =
                mContext.resources.getStringArray(R.array.currency_array)
            for (i in country.indices) {
                list.add(CurrencyData("" + i, country[i]))
                Debug.e("" + i.toString())
            }
            currencyAdapter = CurrencyAdapter(mContext)
            currencyAdapter.addAll(list)
            dialogCurrencySelectionBinding!!.rvCurrency.adapter = currencyAdapter

            dialogCurrencySelectionBinding!!.btnNext.setOnClickListener {


            }

            dialogCurrencySelectionBinding!!.close.setOnClickListener {

                dialogCurrency.dismiss()


            }
            dialogCurrency.setOnShowListener { dialogInterface ->
                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            }

        }

        dialogCurrency.show()
    }

    var dialogLanguageSelectionBinding: DialogLanguageSelectionBinding? = null
    lateinit var dialogLanguage: BottomSheetDialog
    lateinit var languageAdapter: LanguageAdapter

    fun showLanguageDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_language_selection, null)
            dialogLanguageSelectionBinding = DataBindingUtil.bind(v)
            dialogLanguage = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogLanguage.setContentView(v)
            dialogLanguage.setCanceledOnTouchOutside(false)
            dialogLanguage.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            dialogLanguage.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            val list: ArrayList<LanguageData> = ArrayList<LanguageData>()
            val country: Array<String> =
                mContext.resources.getStringArray(R.array.language_array)
            for (i in country.indices) {
                list.add(LanguageData("" + i, country[i]))
                Debug.e("" + i.toString())
            }
            languageAdapter = LanguageAdapter(mContext)
            languageAdapter.addAll(list)
            dialogLanguageSelectionBinding!!.rvLanguage.adapter = languageAdapter

            dialogLanguageSelectionBinding!!.btnNext.setOnClickListener {


            }

            dialogLanguageSelectionBinding!!.close.setOnClickListener {

                dialogLanguage.dismiss()


            }
            dialogLanguage.setOnShowListener { dialogInterface ->
                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            }

        }

        dialogLanguage.show()
    }


    var dialogEditProfileBinding: DialogEditProfileBinding? = null
    lateinit var dialogEdit: BottomSheetDialog

    fun showEditDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_edit_profile, null)
            dialogEditProfileBinding = DataBindingUtil.bind(v)
            dialogEdit = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogEdit.setContentView(v)
            dialogEdit.setCanceledOnTouchOutside(false)
            dialogEdit.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            val list =
                arrayOf("Select", "Mr", "Miss", "Mrs", "Mdm")
            val langAdapter: ArrayAdapter<CharSequence> =
                ArrayAdapter<CharSequence>(mContext, R.layout.item_salutation, list)
            dialogEditProfileBinding!!.spinner.setAdapter(langAdapter)

            dialogEditProfileBinding!!.btnNext.setOnClickListener {


            }
            dialogEditProfileBinding!!.tvDate.setOnClickListener {
                showDatePickerDialog(mContext)

            }
            dialogEditProfileBinding!!.close.setOnClickListener {

                dialogEdit.dismiss()


            }
            dialogEdit.setOnShowListener { dialogInterface ->
                //                                setupFullHeight(v,dialogInterface)
                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

                //BottomSheetBehavior.from(bottomSheet).setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
            }

        }

        dialogEdit.show()
    }

    var dialogDateTimePickerBinding: DialogDateTimePickerBinding? = null
    lateinit var dialogDatePicker: androidx.appcompat.app.AlertDialog
    fun showDatePickerDialog(mContext: Context) {
        if (dialogDateTimePickerBinding == null) {

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
                dialogDatePicker.dismiss()
                dialogEditProfileBinding!!.tvDate.setText(dialogDateTimePickerBinding!!.datePicker1.dayOfMonth.toString() + " / " + dialogDateTimePickerBinding!!.datePicker1.month.inc() + " / " + dialogDateTimePickerBinding!!.datePicker1.year.toString())
            }
            dialogDateTimePickerBinding!!.tvCancel.setOnClickListener {
                dialogDatePicker.dismiss()
            }

//            setUpTabs(mContext)

        }

        dialogDatePicker.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        try {
            dialogDatePicker.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}


