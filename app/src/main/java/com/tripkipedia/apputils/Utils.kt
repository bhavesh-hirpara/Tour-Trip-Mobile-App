package com.tripkipedia.apputils

import android.app.Activity
import android.app.Dialog
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.location.LocationManager
import android.media.MediaScannerConnection
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.preference.PreferenceManager
import android.provider.Settings
import android.text.Editable
import android.text.Html
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.view.inputmethod.InputMethodManager
import android.webkit.MimeTypeMap
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.tripkipedia.BuildConfig
import com.tripkipedia.R
import com.tripkipedia.exceptions.networks.NoInternetException
import java.io.ByteArrayOutputStream
import java.io.File
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
object Utils {
    fun getUriForShare(context: Context, file: File): Uri? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file)
            } else {
                Uri.fromFile(file)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun loadLocalImage(img: ImageView, url: File, context: Context, resId: Int) {
        run {
            val options: RequestOptions = RequestOptions()
                .placeholder(resId)
                .error(resId);
            Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options).into(img)
        }
    }

    val isSDcardMounted: Boolean
        get() {

            val state = Environment.getExternalStorageState()
            return state == Environment.MEDIA_MOUNTED && state != Environment.MEDIA_MOUNTED_READ_ONLY

        }

    fun getHashKey() {
        val sha1 = "0f:04:66:e6:ee:a8:27:cb:3a:c9:42:6f:03:1c:14:e0:fd:fe:ec:45"
        val arr = sha1.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val byteArr = ByteArray(arr.size)

        for (i in arr.indices) {
            byteArr[i] = Integer.decode("0x" + arr[i]).toByte()
        }

        Debug.e("KeyHash 2 : ", Base64.encodeToString(byteArr, Base64.NO_WRAP))
    }

    fun isEmptyString(str: String?): Boolean {
        return str != null && str != ""
    }

    fun setHtmlText(htmlText: String?): String {
        var text: String = ""
        if (htmlText == null) {
            return text
        }
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {

            text = Html.fromHtml(htmlText).toString()
        }
        return text
    }


    /**
     * Create a File for saving an image or video
     *
     * @return Returns file reference
     */
    // To be safe, you should check that the SDCard is mounted
    // using Environment.getExternalStorageState() before doing this.
    // This location works best if you want the created images to be
    // shared
    // between applications and persist after your app has been
    // uninstalled.
    // Create the storage directory if it does not exist
    // Create a media file name
    val outputMediaFile: File?
        get() {
            try {
                val mediaStorageDir: File
                if (isSDcardMounted) {
                    mediaStorageDir = File(Constant.FOLDER_RIDEINN_PATH)
                } else {
                    mediaStorageDir = File(
                        Environment.getRootDirectory(),
                        Constant.FOLDER_NAME
                    )
                }
                if (!mediaStorageDir.exists() && !mediaStorageDir.mkdirs()) {
                    return null
                }

                val mediaFile = File(
                    mediaStorageDir.path
                            + File.separator + Date().time + ".jpg"
                )
                mediaFile.createNewFile()

                return mediaFile
            } catch (e: Exception) {
                return null
            }

        }

    fun setPref(c: Context, pref: String, value: String) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putString(pref, value)
        e.commit()

    }

    fun getPref(c: Context, pref: String, value: String): String? {
        return PreferenceManager.getDefaultSharedPreferences(c).getString(
            pref,
            value
        )
    }

    fun setPref(c: Context, pref: String, value: Float) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putFloat(pref, value)
        e.commit()

    }

    fun getPref(c: Context, pref: String, value: Float): Float? {
        return PreferenceManager.getDefaultSharedPreferences(c).getFloat(
            pref,
            value
        )
    }

    fun setPref(c: Context, pref: String, value: Boolean) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putBoolean(pref, value)
        e.commit()

    }

    fun getPref(c: Context, pref: String, value: Boolean): Boolean {
        return PreferenceManager.getDefaultSharedPreferences(c).getBoolean(
            pref, value
        )
    }

    fun delPref(c: Context, pref: String) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.remove(pref)
        e.commit()
    }

    fun setPref(c: Context, pref: String, value: Int) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putInt(pref, value)
        e.commit()

    }

    fun getPref(c: Context, pref: String, value: Int): Int {
        return PreferenceManager.getDefaultSharedPreferences(c).getInt(
            pref,
            value
        )
    }

    fun setPref(c: Context, pref: String, value: Long) {
        val e = PreferenceManager.getDefaultSharedPreferences(c).edit()
        e.putLong(pref, value)
        e.commit()
    }

    fun getPref(c: Context, pref: String, value: Long): Long {
        return PreferenceManager.getDefaultSharedPreferences(c).getLong(
            pref,
            value
        )
    }

    fun setPref(c: Context, file: String, pref: String, value: String) {
        val settings = c.getSharedPreferences(
            file,
            Context.MODE_PRIVATE
        )
        val e = settings.edit()
        e.putString(pref, value)
        e.commit()
    }

    fun getPref(c: Context, file: String, pref: String, value: String): String? {
        return c.getSharedPreferences(file, Context.MODE_PRIVATE).getString(
            pref, value
        )
    }

    fun validateEmail(target: CharSequence): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            android.util.Patterns.EMAIL_ADDRESS.matcher(target)
                .matches()
        }
    }

    fun validate(target: String, pattern: String): Boolean {
        if (TextUtils.isEmpty(target)) {
            return false
        } else {
            val r = Pattern.compile(pattern)
            return r.matcher(target)
                .matches()
        }
    }

    fun isAlphaNumeric(target: String): Boolean {
        if (TextUtils.isEmpty(target)) {
            return false
        } else {
            val r = Pattern.compile("^[a-zA-Z0-9]*$")
            return r.matcher(target)
                .matches()
        }
    }

    fun isNumeric(target: String): Boolean {
        if (TextUtils.isEmpty(target)) {
            return false
        } else {
            val r = Pattern.compile("^[0-9]*$")
            return r.matcher(target)
                .matches()
        }
    }

    fun getDeviceWidth(context: Context): Int {
        try {
            val metrics = context.resources.displayMetrics
            return metrics.widthPixels
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return 480
    }

    fun getDeviceHeight(context: Context): Int {
        try {
            val metrics = context.resources.displayMetrics
            return metrics.heightPixels
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return 800
    }

    @Throws(NoInternetException::class)
    fun isInternetConnected(mContext: Context?): Boolean {
        var outcome = false
        try {
            if (mContext != null) {
                val cm = mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val networkInfo = cm.activeNetworkInfo
//                for (tempNetworkInfo in networkInfo) {
                if (networkInfo.isConnected) {
                    outcome = true
                }
//                }
            }
        } catch (e: NoInternetException) {
            e.printStackTrace()
        }

        return outcome
    }

    fun getDeviceId(c: Context): String? {
        val deviceId = Constant.NO_DEVICE_ID
        var aid: String?
        try {
            aid = ""
            aid = Settings.Secure.getString(
                c.contentResolver,
                "android_id"
            )

            if (aid == null) {
                aid = deviceId
            } else if (aid.isEmpty()) {
                aid = deviceId
            }

        } catch (e: Exception) {
            sendExceptionReport(e)
            aid = deviceId
        }

        return aid

    }

    fun random(min: Float, max: Float): Float {
        return (min + Math.random() * (max - min + 1)).toFloat()
    }

    fun random(min: Int, max: Int): Int {
        return Math.round((min + Math.random() * (max - min + 1)).toFloat())
    }

    fun hasFlashFeature(context: Context): Boolean {
        return context.packageManager.hasSystemFeature(
            PackageManager.FEATURE_CAMERA_FLASH
        )
    }


    fun hideKeyBoard(c: Context, v: View) {
        val imm = c
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }


    fun getBold(c: Context): Typeface? {
        try {
            return Typeface.createFromAsset(
                c.assets,
                "SF-Pro-Rounded-Bold.ttf"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getRegular(c: Context): Typeface? {
        try {
            return Typeface.createFromAsset(
                c.assets,
                "SF-Pro-Rounded-Regular.ttf"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getLights(c: Context): Typeface? {
        try {
            return Typeface.createFromAsset(
                c.assets,
                "HelveticaNeue Light.ttf"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun getMedium(c: Context): Typeface? {
        try {
            return Typeface.createFromAsset(
                c.assets,
                "SF-Pro-Rounded-Semibold.ttf"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    fun formatNo(str: String): String? {
        val number = removeComma(nullSafe(str))
        return if (!TextUtils.isEmpty(number)) {
            formatToComma(number!!)
        } else number

    }

    fun formatNoDollar(str: String): String? {
        val number = removeComma(nullSafe(str))
        if (!TextUtils.isEmpty(number)) {

            var finalStr = formatToComma(number!!)

            if (!finalStr!!.startsWith("$"))
                finalStr = "$$finalStr"
            return finalStr
        }

        return number

    }

    private fun formatToComma(str: String): String? {
        var number = removeComma(nullSafe(str))
        if (!TextUtils.isEmpty(number)) {

            var finalStr: String
            if (number!!.contains(".")) {
                number = truncateUptoTwoDecimal(number)
                val decimalFormat = DecimalFormat("#.##")
                finalStr = decimalFormat.format(BigDecimal(number))
            } else {
                finalStr = number
            }

            finalStr =
                NumberFormat.getNumberInstance(Locale.US).format(java.lang.Double.valueOf(finalStr))
            return finalStr
        }

        return number
    }

    private fun truncateUptoTwoDecimal(doubleValue: String): String {
        if (doubleValue != null) {
            var result = doubleValue
            val decimalIndex = result.indexOf(".")
            if (decimalIndex != -1) {
                val decimalString = result.substring(decimalIndex + 1)
                if (decimalString.length > 2) {
                    result = doubleValue.substring(0, decimalIndex + 3)
                } else if (decimalString.length == 1) {
                    //                    result = String.format(Locale.ENGLISH, "%.2f",
                    //                            Double.parseDouble(value));
                }
            }
            return result
        }

        return doubleValue
    }

    fun removeComma(str: String): String? {
        var str = str
        try {
            if (!TextUtils.isEmpty(str)) {
                str = str.replace(",".toRegex(), "")
                try {
                    val format = NumberFormat.getCurrencyInstance()
                    val number = format.parse(str)
                    return number.toString()
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        Debug.e("removeComma", "" + str)
        return str

    }

    fun getRowFadeSpeedAnimation(c: Context): LayoutAnimationController {
        val anim = AnimationUtils.loadAnimation(
            c,
            R.anim.raw_fade
        ) as AlphaAnimation

        return LayoutAnimationController(
            anim, 0.3f
        )
    }

    fun sendExceptionReport(e: Exception) {
        e.printStackTrace()

        try {
            // Writer result = new StringWriter();
            // PrintWriter printWriter = new PrintWriter(result);
            // e.printStackTrace(printWriter);
            // String stacktrace = result.toString();
            // new CustomExceptionHandler(c, URLs.URL_STACKTRACE)
            // .sendToServer(stacktrace);
        } catch (e1: Exception) {
            e1.printStackTrace()
        }

    }


    fun getAndroidId(c: Context): String {
        var aid: String?
        try {
            aid = Settings.Secure.getString(
                c.contentResolver,
                "android_id"
            )

            if (aid == null) {
                aid = Constant.NO_DEVICE_ID
            } else if (aid.length <= 0) {
                aid = Constant.NO_DEVICE_ID
            }
        } catch (e: Exception) {
            e.printStackTrace()
            aid = Constant.NO_DEVICE_ID
        }

        Debug.e("", "aid : " + aid!!)

        return aid

    }

    fun getAppVersionCode(c: Context): Int {
        try {
            return c.packageManager.getPackageInfo(c.packageName, 0).versionCode
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return 0

    }

    fun getPhoneModel(): String {
        try {
            return Build.MODEL
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return ""
    }

    fun getPhoneBrand(): String {

        try {
            return Build.BRAND
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return ""
    }

    fun getOsVersion(): String {

        try {
            return Build.VERSION.RELEASE
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return ""
    }

    fun getOsAPILevel(): Int {

        try {
            return Build.VERSION.SDK_INT
        } catch (e: Exception) {
            sendExceptionReport(e)
        }

        return 0
    }

    fun parseCalendarFormat(c: Calendar, pattern: String): String {
        val sdf = SimpleDateFormat(
            pattern,
            Locale.getDefault()
        )
        return sdf.format(c.time)
    }

    fun parseTime(time: Long, pattern: String): String {
        val sdf = SimpleDateFormat(
            pattern,
            Locale.getDefault()
        )
        return sdf.format(Date(time))
    }

    fun parseTime(time: String, pattern: String): Date {
        val sdf = SimpleDateFormat(
            pattern,
            Locale.getDefault()
        )
        try {
            return sdf.parse(time)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return Date()
    }

    fun parseTime(
        time: String, fromPattern: String,
        toPattern: String
    ): String {
        var sdf = SimpleDateFormat(
            fromPattern,
            Locale.getDefault()
        )
        try {
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(toPattern, Locale.getDefault())
            return sdf.format(d)
        } catch (e: Exception) {
            Log.i("parseTime", "" + e.message)
        }

        return ""
    }


    fun parseTime(time: String): String {
        return parseTime(time, "yyyy-MM-dd'T'HH:mm:ss", "MMM dd")
    }

    fun getTransactionDate(time: String): String {
        return parseTime(time, "yyyy-MM-dd'T'HH:mm:ss.SSS", "MM/dd/yyyy hh:mm")
    }

    fun getTransactionStatus(status: String): String {
        return status
    }

    fun getStartDate(time: String): String {
        return " " + parseTime(time, "yyyy-MM-dd'T'HH:mm:ss", "MMM dd") + " - "
    }

    fun getEndDate(time: String): String {
        return " " + parseTime(time, "yyyy-MM-dd'T'HH:mm:ss", "MMM dd")
    }

    fun getShareDateTime(time: String): String {
        return parseTime(time, "yyyy-MM-dd'T'HH:mm:ss", "dd MMMM yyyy hh:mm a")
    }

    fun parseTimeUTCtoDefault(time: String, pattern: String): Date {
        var sdf = SimpleDateFormat(
            pattern,
            Locale.getDefault()
        )
        try {
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(pattern, Locale.getDefault())
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.parse(sdf.format(d))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Date()
    }

    fun parseTimeUTCtoDefault(time: Long): Date {

        try {
            val cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            cal.timeInMillis = time
            val d = cal.time
            val sdf = SimpleDateFormat()
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.parse(sdf.format(d))
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return Date()
    }

    fun parseTimeUTCtoDefault(
        time: String, fromPattern: String,
        toPattern: String
    ): String {

        var sdf = SimpleDateFormat(
            fromPattern,
            Locale.getDefault()
        )
        try {
            sdf.timeZone = TimeZone.getTimeZone("UTC")
            val d = sdf.parse(time)
            sdf = SimpleDateFormat(toPattern, Locale.getDefault())
            sdf.timeZone = Calendar.getInstance().timeZone
            return sdf.format(d)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun daysBetween(startDate: Date, endDate: Date): Long {
        val sDate = getDatePart(startDate)
        val eDate = getDatePart(endDate)

        var daysBetween: Long = 0
        while (sDate.before(eDate)) {
            sDate.add(Calendar.DAY_OF_MONTH, 1)
            daysBetween++
        }
        return daysBetween
    }

    fun getDatePart(date: Date): Calendar {
        val cal = Calendar.getInstance()       // get calendar instance
        cal.time = date
        cal.set(Calendar.HOUR_OF_DAY, 0)            // set hour to midnight
        cal.set(Calendar.MINUTE, 0)                 // set minute in hour
        cal.set(Calendar.SECOND, 0)                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0)            // set millisecond in second
        return cal
    }

    fun nullSafe(content: String?): String {
        if (content == null) {
            return ""
        }

        return if (content.equals("null", ignoreCase = true)) {
            ""
        } else content

    }

    fun nullSafe(content: String, defaultStr: String): String {
        if (TextUtils.isEmpty(content)) {
            return defaultStr
        }

        return if (content.equals("null", ignoreCase = true)) {
            defaultStr
        } else content

    }

    fun nullSafeDash(content: String): String {
        if (TextUtils.isEmpty(content)) {
            return "-"
        }

        return if (content.equals("null", ignoreCase = true)) {
            "-"
        } else content

    }

    fun nullSafe(content: Int, defaultStr: String): String {
        return if (content == 0) {
            defaultStr
        } else "" + content

    }


    fun isGPSProviderEnabled(context: Context): Boolean {
        val locationManager = context
            .getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun isNetworkProviderEnabled(context: Context): Boolean {
        val locationManager = context
            .getSystemService(Context.LOCATION_SERVICE) as LocationManager

        return locationManager
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    fun isLocationProviderEnabled(context: Context): Boolean {
        return isGPSProviderEnabled(context) || isNetworkProviderEnabled(context)
    }

    fun isLocationProviderRequired(context: Context): Boolean {
        val lang = getPref(context, Constant.USER_LONGITUDE, "")
        val lat = getPref(context, Constant.USER_LATITUDE, "")

        return !(lat!!.isNotEmpty() && lang!!.length > 0)

    }

    fun isUserLoggedIn(c: Context): Boolean {
        return getUid(c)!!.isNotEmpty()
    }

    fun isFirsTime(c: Context): Boolean {
        return getPref(c, Constant.IS_FIRST_TIME, true)
    }

    fun getUid(c: Context): String? {
        return getPref(c, RequestParamsUtils.AUTHENTICATIONTOKEN, "")
    }

    fun clearLoginCredentials(c: Context) {
        delPref(c, RequestParamsUtils.USER_ID)
        delPref(c, RequestParamsUtils.SESSION_ID)
        delPref(c, RequestParamsUtils.TOKEN)
        delPref(c, RequestParamsUtils.USER_FIRST_NAME)
        delPref(c, RequestParamsUtils.USER_LAST_NAME)
        delPref(c, Constant.LOGIN_INFO)
        delPref(c, RequestParamsUtils.AUTHENTICATIONTOKEN)
        delPref(c, Constant.USER_LATITUDE)
        delPref(c, Constant.USER_LONGITUDE)


        val nMgr = c.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nMgr.cancelAll()
    }


    fun asList(str: String): ArrayList<String?> {

        return ArrayList<String?>(
            Arrays.asList<String>(
                *str
                    .split("\\s*,\\s*".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            )
        )
    }

    fun implode(data: ArrayList<String>): String {
        try {
            var devices = ""
            for (iterable_element in data) {
                devices = "$devices,$iterable_element"
            }

            if (devices.length > 0 && devices.startsWith(",")) {
                devices = devices.substring(1)
            }

            return devices
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return ""
    }

    fun scanMediaForFile(context: Context, filePath: String) {
        resetExternalStorageMedia(context, filePath)
        notifyMediaScannerService(context, filePath)
    }

    fun resetExternalStorageMedia(
        context: Context,
        filePath: String
    ): Boolean {
        try {
            if (Environment.isExternalStorageEmulated())
                return false
            val uri = Uri.parse("file://" + File(filePath))
            val intent = Intent(Intent.ACTION_MEDIA_MOUNTED, uri)

            context.sendBroadcast(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

        return true
    }

    fun notifyMediaScannerService(
        context: Context,
        filePath: String
    ) {

        MediaScannerConnection.scanFile(context, arrayOf(filePath), null) { path, uri ->
            Debug.i("ExternalStorage", "Scanned $path:")
            Debug.i("ExternalStorage", "-> uri=$uri")
        }
    }

    fun cancellAllNotication(context: Context) {

        val notificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }

    fun toInitCap(param: String?): String? {
        try {
            if (param != null && param.length > 0) {
                val charArray = param.toCharArray() // convert into char
                // array
                charArray[0] = Character.toUpperCase(charArray[0]) // set
                // capital
                // letter to
                // first
                // position
                return String(charArray) // return desired output
            } else {
                return ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return param
    }

    fun toGetInitChar(param: String?): String? {
        try {
            if (param != null && param.length > 0) {
                val charArray = param[0] // convert into char
                // array
                //                charArray[0] = Character.toUpperCase(charArray[0]); // set
                // capital
                // letter to
                // first
                // position
                val valstr = charArray.toString()
                return valstr // return desired output
            } else {
                return ""
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return param
    }

    fun encodeTobase64(image: Bitmap): String {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        val imageEncoded = Base64.encodeToString(b, Base64.DEFAULT)

        Debug.e("LOOK", imageEncoded)
        return imageEncoded
    }

    fun decodeBase64(input: String): Bitmap {
        val decodedByte = Base64.decode(input, 0)
        return BitmapFactory
            .decodeByteArray(decodedByte, 0, decodedByte.size)
    }

    fun getExtenstion(urlPath: String): String {
        return if (urlPath.contains(".")) {
            urlPath.substring(urlPath.lastIndexOf(".") + 1)
        } else ""

    }

    fun getFileName(urlPath: String): String {
        return if (urlPath.contains(".")) {
            urlPath.substring(urlPath.lastIndexOf("/") + 1)
        } else ""

    }

    fun dpToPx(context: Context, value: Int): Float {
        val r = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            r.displayMetrics
        )
    }

    fun spToPx(context: Context, value: Int): Float {
        val r = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            value.toFloat(),
            r.displayMetrics
        )
    }


    fun getMimeType(url: String): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        Debug.e("type", "" + type!!)
        return type
    }

    fun isJPEGorPNG(url: String): Boolean {
        try {
            val type = getMimeType(url)
            val ext = type!!.substring(type.lastIndexOf("/") + 1)
            if (ext.equals("jpeg", ignoreCase = true) || ext.equals(
                    "jpg",
                    ignoreCase = true
                ) || ext.equals("png", ignoreCase = true)
            ) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return true
        }

        return false
    }

    fun getFileSize(url: String): Double {
        val file = File(url)

        // Get length of file in bytes
        val fileSizeInBytes = file.length().toDouble()
        // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
        val fileSizeInKB = fileSizeInBytes / 1024
        // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        val fileSizeInMB = fileSizeInKB / 1024

        Debug.e("fileSizeInMB", "" + fileSizeInMB)
        return fileSizeInMB
    }

    fun getAsteriskName(str: String): String {
        var str = str
        val n = 4
        str = nullSafe(str)
        val fStr = StringBuilder()
        if (!TextUtils.isEmpty(str)) {
            if (str.length > n) {
                fStr.append(str.substring(0, n))
                for (i in 0 until str.length - n) {
                    fStr.append("*")
                }

                return fStr.toString()
            } else {
                fStr.append(str.substring(0, str.length - 1))
            }
        }
        return str
    }


    fun getUserToken(c: Context): String? {
        return getPref(c, RequestParamsUtils.TOKEN, "")
    }

    fun getUserAuthToken(c: Context): String? {
        return getPref(c, RequestParamsUtils.AUTHENTICATIONTOKEN, "")
    }

    fun deleteUserAuthToken(c: Context) {
        delPref(c, RequestParamsUtils.AUTHENTICATIONTOKEN)
    }

    fun setLatLong(context: Context, long: Float, lat: Float) {
        setPref(context, Constant.USER_LONGITUDE, long)
        setPref(context, Constant.USER_LATITUDE, lat)
    }

    fun getLatitude(context: Context) = getPref(context, Constant.USER_LATITUDE, 0.0f)

    fun getLongitude(context: Context) = getPref(context, Constant.USER_LONGITUDE, 0.0f)

    fun delLatLong(context: Context) {
        delPref(context, Constant.USER_LONGITUDE)
        delPref(context, Constant.USER_LATITUDE)
    }

    fun showToast(context: Context, msg: String) {
        val toast = Toast.makeText(
            context,
            msg, Toast.LENGTH_SHORT
        )
        toast.setGravity(Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL, 0, 100)
        toast.show()
    }

    fun getCurrency(): String {
        return "$"
    }

    fun getAmount(amount: Int): String {
        return getCurrency() + " " + formatToFloat(amount.toDouble())
    }

    fun formatToFloat(flt: Double?): String {
        val decimalFormat = DecimalFormat("0.00")
        decimalFormat.isGroupingUsed = true
        decimalFormat.groupingSize = 3
        return decimalFormat.format(flt)
    }

    fun shareOn(shareText: String, shareMode: String, context: Context) {
        if (shareMode == Constant.SHARE_FACEBOOK) {
//            Utils.copyText(getActivity(),shareText,"Description copied! You can paste here")
            shareOnFb(shareText, context);
        } else if (shareMode == Constant.SHARE_TWITTER) {
            shareOnTwitter(shareText, context);
        } else if (shareMode == Constant.SHARE_INSTAGRAM) {
            shareOnInstagram(shareText, context);
        } else if (shareMode == Constant.SHARE_OTHER) {
            shareImageOther(shareText, context);
        }
    }

    private fun shareImageOther(shareText: String, context: Context) {
        try {
            val intent = Intent(Intent.ACTION_SEND)
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            intent.setType("text/plain");
            context.startActivity(Intent.createChooser(intent, "Share"));
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun shareOnInstagram(shareText: String, context: Context) {
        try {
            val appPackageName = "com.instagram.android"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            if (isPackageInstalled("" + appPackageName, context)) {
                intent.setPackage("" + appPackageName)
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Please Install Instagram Application", Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun shareOnFb(shareText: String, context: Context) {
        try {
            val appPackageName = "com.facebook.katana"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            if (isPackageInstalled("" + appPackageName, context)) {
                intent.setPackage("" + appPackageName)
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Please Install fb Application", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun shareOnTwitter(shareText: String, context: Context) {
        try {
            val appPackageName = "com.twitter.android"
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, shareText);
            if (isPackageInstalled("" + appPackageName, context)) {
                intent.setPackage("" + appPackageName)
                context.startActivity(intent)
            } else {
                Toast.makeText(context, "Please Install Twitter Application", Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun isPackageInstalled(packagename: String, context: Context): Boolean {
        val pm = context.packageManager
        try {
            pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES)
            return true
        } catch (e: PackageManager.NameNotFoundException) {
            return false
        }
    }

    fun showSpinner(
        mContext: Context?,
        title: String, tv: TextView,
        data: ArrayList<Spinner>, isFilterable: Boolean, callback: SpinnerCallback?
    ) {
        val a = Dialog(mContext!!)
        val w = a.window
        a.requestWindowFeature(Window.FEATURE_NO_TITLE)
        a.setContentView(R.layout.spinner)
        w!!.setBackgroundDrawableResource(android.R.color.transparent)

        val lblselect = w.findViewById<View>(R.id.dialogtitle) as TextView
        lblselect.text = title.replace("*", "").trim { it <= ' ' }

        //        TextView dialogClear = (TextView) w.findViewById(R.id.dialogClear);
        //        dialogClear.setVisibility(View.VISIBLE);
        //        dialogClear.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View view) {
        //                tv.setText("");
        //                tv.setTag(null);
        //
        //                a.dismiss();
        //            }
        //        });

        val editSearch = w.findViewById<View>(R.id.editSearch) as EditText
        if (isFilterable) {
            editSearch.visibility = View.VISIBLE
        } else {
            editSearch.visibility = View.GONE
        }

        val adapter = SpinnerAdapter(mContext!!)
        adapter.setFilterable(isFilterable)
        val lv = w.findViewById<View>(R.id.lvSpinner) as ListView
        lv.adapter = adapter
        adapter.addAll(data)

        lv.onItemClickListener = AdapterView.OnItemClickListener { adapterview, view, position, l ->
            val selList = ArrayList<Spinner>()
            selList.add(adapter.getItem(position))

            tv.text = adapter.getItem(position).title
            tv.tag = adapter.getItem(position).ID

            callback?.onDone(selList)

            a.dismiss()
        }

        editSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                if (editable.toString().trim { it <= ' ' }.length >= 1) {
                    adapter.filter!!.filter(editable.toString().trim { it <= ' ' })
                } else {
                    adapter.filter!!.filter("")
                }

            }
        })

        a.show()
    }


    fun loadImage(img: ImageView, url: String, context: Context, resId: Int) {
        run {
            val options: RequestOptions = RequestOptions()
                .placeholder(resId)
                .error(resId);
            Glide.with(context).load(url).apply(options).into(img)
        }
    }

    fun loadImage(img: ImageView, context: Context, resId: Int) {
        run {
            Glide.with(context).load(resId).into(img)
        }
    }

    fun isEventFree(rate: Int?): Boolean {
        if (rate != null) {
            return rate <= 0
        }
        return false
    }

    fun getEventAmount(rate: Int?): String {
        if (rate != null) {
            if (rate <= 0) {
                return "Free"
            } else {
                return getAmount(rate)
            }
        } else {
            return ""
        }
    }

    fun getAgeRequirement(age: Int?): String {
        if (age != null) {
            return " $age+";
        } else {
            return ""
        }
    }

    fun getDistanceVenueName(distance: Double?, venueName: String?): String {
        return "$distance mi - $venueName";
    }

    fun getAttending(attendingCount: Int?, notAttendingCount: Int?, maybeCount: Int?): String {
        return " Yes($attendingCount) No($notAttendingCount) May be($maybeCount)"
    }

    fun getAdvertisementImageUrl(imgUrl: String?): String {
        if (imgUrl != null) {
            if (Debug.DEBUG_ADVERTISEMENT) {
                return imgUrl.replace(
                    "localhost:44362",
                    "http://trichordal-development.azurewebsites.net"
                )
            } else {
                return imgUrl
            }
        }
        return ""
    }

    fun getEventModes(eventMode: Int?): String {
        if (eventMode == 0) {
            return Constant.PUBLIC
        } else if (eventMode == 1) {
            return Constant.PRIVATE
        }
        return " $eventMode"
    }

    fun getYoutubeThumbail(youtubeUrl: String): String {
        var uri: Uri = Uri.parse(youtubeUrl);
        var vId: String = uri.getQueryParameter("v")!!;
        var iUrl: String = "http://img.youtube.com/vi/" + vId + "/0.jpg";
        return iUrl
    }

    fun getEventStatuses(eventStatus: Int?): String {
        if (eventStatus == 0) {
            return Constant.INACTIVE
        } else if (eventStatus == 1) {
            return Constant.ACTIVE
        } else if (eventStatus == 2) {
            return Constant.REJECTED
        }
        return " $eventStatus"
    }

    fun getTotalAvailableTickets(tickets: Int?): String {
        return " $tickets"
    }

    fun goToMap(mContext: Context, lat: String, lng: String) {


        var gmmIntentUri: Uri = Uri.parse("geo:" + lat + "," + lng);
        var mapIntent: Intent = Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(mContext.packageManager) != null) {
            mContext.startActivity(mapIntent);
        }
    }

    fun composeEmail(mContext: Context, addresses: Array<String>, subject: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "*/*"
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, subject)
//            putExtra(Intent.EXTRA_STREAM, attachment)
        }
        if (intent.resolveActivity(mContext.packageManager) != null) {
            mContext.startActivity(intent)
        }
    }

    fun dialPhoneNumber(mContext: Context, phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(mContext.packageManager) != null) {
            mContext.startActivity(intent)
        }
    }

    fun setStatusBarGradient(activity: Activity, res: Int, flag: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = activity.window
            val background = ContextCompat.getDrawable(activity, res)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
//            window.navigationBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
            if (flag) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            window.setBackgroundDrawable(background)
        }
    }
}
