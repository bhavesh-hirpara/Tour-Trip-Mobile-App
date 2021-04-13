package com.tripkipedia.ui.view

import android.graphics.Rect
import android.os.Bundle
import android.widget.EditText
import com.tripkipedia.R
import com.tripkipedia.apputils.UserPermissions
import com.tripkipedia.apputils.Utils
import com.tripkipedia.base.view.BaseActivity

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

//    var finalHost: NavHostFragment? = null

    fun init() {
        val token = Utils.getUserAuthToken(activity)
//        if (token != null && token.isNotEmpty()) {
            UserPermissions.getUserRole(applicationContext)
//            finalHost = NavHostFragment.create(R.navigation.events_graph)
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.content,finalHost!!)
//                    .setPrimaryNavigationFragment(finalHost) // this is the equivalent to app:defaultNavHost="true"
//                    .commit()
//        } else {
//            finalHost = NavHostFragment.create(com.admision.R.navigation.authentication_graph)
//            supportFragmentManager.beginTransaction()
//                    .replace(com.admision.R.id.content,finalHost!!)
//                    .setPrimaryNavigationFragment(finalHost) // this is the equivalent to app:defaultNavHost="true"
//                    .commit()
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        try {
//            when (Navigation.findNavController(this,finalHost!!.id)
//                    .currentDestination!!.id) {
//                R.id.findEventsFragment ->
//                    for (item in finalHost!!.getChildFragmentManager().getFragments()) {
//                        if (item is FindEventsFragment && item.closeDrawer()) {
//                            return
//                        }
//                    }
//                R.id.loginFragment ->
//                    super.onBackPressed()
//
//            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onBackPressed()
    }

    protected fun getLocationOnScreen(mEditText: EditText): Rect {
        val mRect = Rect()
        val location = IntArray(2)

        mEditText.getLocationOnScreen(location)

        mRect.left = location[0]
        mRect.top = location[1]
        mRect.right = location[0] + mEditText.width
        mRect.bottom = location[1] + mEditText.height

        return mRect
    }


}
