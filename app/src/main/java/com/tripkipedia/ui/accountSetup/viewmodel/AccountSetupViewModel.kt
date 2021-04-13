package com.tripkipedia.ui.accountSetup.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.drivo.ui.accountSetup.utils.InterestAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tripkipedia.R
import com.tripkipedia.apputils.Debug
import com.tripkipedia.apputils.UriHelper
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.viewmodel.BaseViewModel
import com.tripkipedia.databinding.*
import com.tripkipedia.interfaces.TopBarClickListener
import com.tripkipedia.ui.accountSetup.datamodel.Contry_Data
import com.tripkipedia.ui.accountSetup.datamodel.Interest_Data
import com.tripkipedia.ui.accountSetup.utils.CountryAdapter
import com.tripkipedia.ui.accountSetup.view.AccountSetupActivity
import com.tripkipedia.ui.dashboard.view.DashboardActivity
import java.io.File
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.iterator

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class AccountSetupViewModel(application: Application) : BaseViewModel(application) {

    private lateinit var binder: ActivityAccountSetupBinding
    lateinit var mContext: Context
    val data: ArrayList<Interest_Data> = ArrayList<Interest_Data>()
    lateinit var interestAdapter: InterestAdapter
    var flag:Int?=0


    fun setBinder(
        binder: ActivityAccountSetupBinding
    ) {
        this.binder = binder
        this.mContext = binder.root.context
        init()
    }


    private fun init() {
        binder.topbar.isTextShow = true
        binder.topbar.isBackShow = true
        binder.topbar.tvTitle.setText("Account Setup")
        binder.viewClickHandler = ViewClickHandler()
        this.binder.topbar.topBarClickListener = SlideMenuClickListener()

        val list =
            arrayOf("Select", "Mr", "Miss", "Mrs", "Mdm")
        val langAdapter: ArrayAdapter<CharSequence> =
            ArrayAdapter<CharSequence>(mContext, R.layout.item_salutation, list)
        binder.spinner.setAdapter(langAdapter)


        val HashMap = HashMap<String, Int>()
        HashMap.put("Festivals", R.mipmap.festivals)
        HashMap.put("Events", R.mipmap.events)
        HashMap.put("Workshops", R.mipmap.workshopes)
        HashMap.put("Games", R.mipmap.games)
        HashMap.put("Fitness", R.mipmap.fitness)
        HashMap.put("Pet Friendly", R.mipmap.pet_friendly)
        HashMap.put("Cafes", R.mipmap.cafes)

        for ((key, value) in HashMap) {
            Debug.e("msg", key)
            data.add(Interest_Data(key, value))
        }
        interestAdapter = InterestAdapter(mContext)
        interestAdapter.addAll(data)
        binder.rvInterest.adapter = interestAdapter

        interestAdapter.setEventListener(object : InterestAdapter.EventListener {
            override fun onItemClick(pos: Int) {
                binder.btnNext.isEnabled=true
                binder.btnNext.setBackgroundResource(R.mipmap.btn_next_filled)
            }


        })


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

    inner class ViewClickHandler {

        fun onNextClick() {
            if (binder.cvProfile.visibility == View.VISIBLE) {
                binder.cvProfile.visibility = View.GONE

                binder.cvInterest.visibility = View.VISIBLE
                binder.flStepView.visibility = View.VISIBLE
                binder.btnNext.isEnabled=false
                binder.btnNext.setBackgroundResource(R.mipmap.button_next_unfilled)
            }
            else {

                if (binder.cvInterest.visibility == View.VISIBLE) {
                    if(flag==1)
                    {
                        val i = Intent(mContext, DashboardActivity::class.java)
                        (mContext as Activity).startActivity(i)
                    }else{
                        binder.tv1.visibility = View.GONE
                        binder.btnNext.setBackgroundResource(R.mipmap.button_next_unfilled)
                        binder.btnNext.isEnabled=false
                        binder.imgTickmark.visibility = View.VISIBLE
                        binder.tvSecondStep.setImageResource(R.mipmap.circle_white)
                        binder.tv2.setTextColor(Color.parseColor("#161970"))
                        binder.btnNext.setText("Done")
                        changeAdapterData()
                    }



                }

            }
        }

        fun onSelectDateClick() {
            showDatePickerDialog(mContext)
        }
        fun onCameraClick() {
            showPictureChooser()
        }
        fun onCountrySelectionClick() {
            showCountryDialog()
        }

    }

    private fun changeAdapterData() {
        data.clear()
        val HashMap = HashMap<String, Int>()
        HashMap.put("Asian\nFood", R.mipmap.asian_food)
        HashMap.put("Japanese\nFood", R.mipmap.japanes_food)
        HashMap.put("Korean\nFood", R.mipmap.korean_food)
        HashMap.put("German\nFood", R.mipmap.german_food)
        HashMap.put("Western\nFood", R.mipmap.western_food)
        HashMap.put("Vegan\nFood", R.mipmap.vegan_food)
        HashMap.put("Snacks", R.mipmap.snacks)
        HashMap.put("Desserts", R.mipmap.desserts)
        HashMap.put("Bubble Tea", R.mipmap.bubble_tea)

        for ((key, value) in HashMap) {
            Debug.e("msg", key)
            data.add(Interest_Data(key, value))
        }
        interestAdapter = InterestAdapter(mContext)
        interestAdapter.addAll(data)
        binder.rvInterest.adapter = interestAdapter
        interestAdapter.setEventListener(object : InterestAdapter.EventListener {
            override fun onItemClick(pos: Int) {
                binder.btnNext.isEnabled=true
                flag=1
                binder.btnNext.setBackgroundResource(R.mipmap.btn_next_filled)
            }


        })
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
                binder.tvDate.setText(dialogDateTimePickerBinding!!.datePicker1.dayOfMonth.toString() + " / " + dialogDateTimePickerBinding!!.datePicker1.month.inc() + " / " + dialogDateTimePickerBinding!!.datePicker1.year.toString())
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

    companion object {
        private const val TAG = "AccountSetupActivity"
    }




    var fileUri: File? = null
    val REQ_CAPTURE_IMAGE = 4470
    val REQ_PICK_IMAGE = 4569
    var type = ""


    fun showPictureChooser() {
        val pd: AlertDialog.Builder =
            AlertDialog.Builder((mContext as AccountSetupActivity), R.style.MyAlertDialogStyle)
        val binding = DataBindingUtil.inflate<DialogPicChooserBinding>(
            LayoutInflater.from(mContext),
            R.layout.dialog_pic_chooser, null, false
        )

        pd.setView(binding.root)
        val dialog = pd.create()

        binding.tvChooseGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            intent.flags = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            try {
                (mContext as AccountSetupActivity).startActivityForResult(
                    Intent.createChooser(
                        intent,
                        mContext!!.getString(R.string.err_select_image)
                    ), REQ_PICK_IMAGE
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            dialog.dismiss()
        }

        binding.tvChooseCamera.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.flags = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
            intent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true)
            fileUri = File(Utils.outputMediaFile!!.absolutePath)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Utils.getUriForShare(mContext!!, fileUri!!))
            try {
                (mContext as AccountSetupActivity).startActivityForResult(
                    Intent.createChooser(
                        intent,
                        mContext!!.getString(R.string.err_select_image)
                    ), REQ_CAPTURE_IMAGE
                )
            } catch (ex: Exception) {
                ex.printStackTrace()
            }
            dialog.dismiss()
        }

        dialog.show()

    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQ_CAPTURE_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    if (fileUri == null || !fileUri!!.exists()) {
                        val tmpFileUri = data!!.data
                        Debug.e("", "tmp_fileUri : " + tmpFileUri!!.path!!)
                        // Debug.e("", "fileUri : " + fileUri.getPath());
                        val selectedImagePath = UriHelper.getPath(
                            mContext as Activity, tmpFileUri
                        )
                        fileUri = File(selectedImagePath!!)

                    }
                    if (fileUri != null && fileUri!!.exists()) {
                        afterImageSelected(fileUri)
//                        Utils.loadImage(binder.imgTextileService,fileUri!!.path,mContext,com.texon.R.mipmap.user_placeholder)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        } else if (requestCode == REQ_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            try {
                val tmpFileUri = data!!.data
                Debug.e("", "tmp_fileUri : " + tmpFileUri!!.path!!)
                val selectedImagePath = UriHelper.getPath(
                    mContext as Activity,
                    tmpFileUri
                )

                fileUri = File(selectedImagePath!!)
                if (fileUri != null && fileUri!!.exists()) {
                    afterImageSelected(fileUri)

                    Debug.e("", "fileUri : " + fileUri!!.absolutePath)
                    /*  addEventViewModel!!.onImagePicked(fileUri!!)*/
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    private fun afterImageSelected(fileUri: File?) {
        Utils.loadLocalImage(binder.profileImage, fileUri!!, mContext, R.mipmap.placeholder)
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

