package com.tripkipedia.ui.myProfile.viewmodel

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
import com.tripkipedia.ui.accountSetup.datamodel.Contry_Data
import com.tripkipedia.ui.accountSetup.utils.CountryAdapter
import com.tripkipedia.ui.myProfile.datamodel.CreditCardData
import com.tripkipedia.ui.myProfile.datamodel.WalletData
import com.tripkipedia.ui.myProfile.passportDetails.view.PassportDetailsActivity
import com.tripkipedia.ui.myProfile.utils.CreditCardAdapter
import com.tripkipedia.ui.myProfile.utils.WalletAdapter
import java.util.ArrayList

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MyProfileViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityMyProfileBinding
    lateinit var mContext: Context


    fun setBinder(binder: ActivityMyProfileBinding) {
        this.binder = binder
        this.mContext = binder.root.context
        binder.viewModel = this
        binder.viewClickHandler = ViewClickHandler()
        binder.topbar.isMenuShow = true
        binder.topbar.topBarClickListener = SlideMenuClickListener()
        initDrawer(mContext)
        init()

    }

    fun init() {

    }

    inner class ViewClickHandler {
        fun onEditClick() {

            showEditDialog()
        }

        fun onMyPaymentClick() {

            showMyPaymentDialog()
        }

        fun onMyPassportClick() {

            val i = Intent(mContext, PassportDetailsActivity::class.java)
            i.putExtra("flag", "MyProfileActivity")
            (mContext as Activity).startActivity(i)
        }
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
            dialogEdit.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            dialogEdit.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            val list =
                arrayOf("Select", "Mr", "Miss", "Mrs", "Mdm")
            val langAdapter: ArrayAdapter<CharSequence> =
                ArrayAdapter<CharSequence>(mContext, R.layout.item_salutation, list)
            dialogEditProfileBinding!!.spinner.setAdapter(langAdapter)

            dialogEditProfileBinding!!.btnNext.setOnClickListener {


            }
            dialogEditProfileBinding!!.tvCountrySelection.setOnClickListener {
                showCountryDialog()
            }
            dialogEditProfileBinding!!.tvDate.setOnClickListener {
                showDatePickerDialog(mContext)

            }
            dialogEditProfileBinding!!.close.setOnClickListener {

                dialogEdit.dismiss()


            }
            dialogEdit.setOnShowListener { dialogInterface ->

                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            }

        }

        dialogEdit.show()
    }

    var dialogMyPaymentBinding: DialogMyPaymentBinding? = null
    lateinit var dialogMyPayment: BottomSheetDialog
    val dataCreditCard: ArrayList<CreditCardData> = ArrayList<CreditCardData>()
    lateinit var creditCardAdapter: CreditCardAdapter
    val dataWallet: ArrayList<WalletData> = ArrayList<WalletData>()
    lateinit var walletAdapter: WalletAdapter

    fun showMyPaymentDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_my_payment, null)
            dialogMyPaymentBinding = DataBindingUtil.bind(v)
            dialogMyPayment = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogMyPayment.setContentView(v)
            dialogMyPayment.setCanceledOnTouchOutside(false)
            dialogMyPayment.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            dialogMyPayment.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

            val HashMap = HashMap<String, Int>()
            HashMap.put("DBS / POSB (*0912)", R.drawable.ic_visa)
            HashMap.put("CitiBank (*1262)", R.drawable.ic_visa)

            dataCreditCard.clear()
            for ((key, value) in HashMap) {
                Debug.e("msg", key)
                dataCreditCard.add(CreditCardData(key, value))
            }
            creditCardAdapter = CreditCardAdapter(mContext)
            creditCardAdapter.addAll(dataCreditCard)
            dialogMyPaymentBinding!!.rvCreditCard.adapter = creditCardAdapter


            val HashMap1 = HashMap<String, Int>()
            HashMap1.put("PayPal", R.mipmap.paypal)
            HashMap1.put("RazerPay", R.mipmap.razer)
            HashMap1.put("WeChat Pay", R.mipmap.wechat)
            HashMap1.put("AliPay", R.mipmap.ali)
            HashMap1.put("GrabPay", R.mipmap.grab)

            dataWallet.clear()
            for ((key, value) in HashMap1) {
                Debug.e("msg", key)
                dataWallet.add(WalletData(key, value))
            }
            walletAdapter = WalletAdapter(mContext)
            walletAdapter.addAll(dataWallet)
            dialogMyPaymentBinding!!.rvPay.adapter = walletAdapter


            dialogMyPaymentBinding!!.close.setOnClickListener {

                dialogMyPayment.dismiss()


            }
            dialogMyPayment.setOnShowListener { dialogInterface ->
                //                                setupFullHeight(v,dialogInterface)
                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            }

        }

        dialogMyPayment.show()
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


        }

        dialogDatePicker.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        try {
            dialogDatePicker.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    var dialogCountrySelectionBinding: DialogCountrySelectionBinding? = null
    lateinit var dialogCountrySelection: BottomSheetDialog
    lateinit var countryAdapter: CountryAdapter

    fun showCountryDialog() {

        if (true) {
            val v: View = (mContext as Activity).getLayoutInflater()
                .inflate(R.layout.dialog_country_selection, null)
            dialogCountrySelectionBinding = DataBindingUtil.bind(v)
            dialogCountrySelection = BottomSheetDialog(mContext as Activity, R.style.DialogStyle)
            dialogCountrySelection.setContentView(v)
            dialogCountrySelection.setCanceledOnTouchOutside(false)
            dialogCountrySelection.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.BLACK));
            dialogCountrySelection.getWindow()
                ?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
            val list: ArrayList<Contry_Data> = ArrayList<Contry_Data>()
            val country: Array<String> =
                mContext.resources.getStringArray(R.array.countries_array)
            for (i in country.indices) {
                list.add(Contry_Data("" + i, country[i]))
                Debug.e("" + i.toString())
            }
            countryAdapter =
                CountryAdapter(mContext)
            countryAdapter.addAll(list)
            dialogCountrySelectionBinding!!.rvCountry.adapter = countryAdapter

            dialogCountrySelectionBinding!!.btnSave.setOnClickListener {
                dialogCountrySelection.dismiss()
            }

            dialogCountrySelectionBinding!!.close.setOnClickListener {

                dialogCountrySelection.dismiss()


            }
            dialogCountrySelection.setOnShowListener { dialogInterface ->
                val bottomSheetBehavior = BottomSheetBehavior.from((v.getParent()) as View)
                val bottomSheetDialog = dialogInterface as BottomSheetDialog
                val bottomSheet = bottomSheetDialog.findViewById<View>(
                    com.google.android.material.R.id.design_bottom_sheet
                )
                bottomSheet?.setBackgroundColor(Color.TRANSPARENT)
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED


            }

        }

        dialogCountrySelection.show()
    }
}


