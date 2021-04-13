
package com.tripkipedia.custom

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Bhavesh Hirpara on 25-05-2020
 */
class SimpleListDividerDecorator
/**
 * Constructor.
 *
 * @param horizontalDivider horizontal divider drawable
 * @param verticalDivider   vertical divider drawable
 * @param overlap           whether the divider is drawn overlapped on bottom (or right) of the item.
 */
(private val mHorizontalDrawable: Drawable?,private val mVerticalDrawable: Drawable?,private val mOverlap: Boolean) : RecyclerView.ItemDecoration() {
    private val mHorizontalDividerHeight: Int
    private val mVerticalDividerWidth: Int

    /**
     * Constructor.
     *
     * @param divider horizontal divider drawable
     * @param overlap whether the divider is drawn overlapped on bottom of the item.
     */
    constructor(divider: Drawable?,overlap: Boolean) : this(divider,null,overlap) {}

    init {
        mHorizontalDividerHeight = mHorizontalDrawable?.intrinsicHeight ?: 0
        mVerticalDividerWidth = mVerticalDrawable?.intrinsicWidth ?: 0
    }

    override fun onDrawOver(c: Canvas,parent: RecyclerView,state: RecyclerView.State) {
        val childCount = parent.childCount
        if (childCount != 0) {
            var xPositionThreshold = 0.0f
            var yPositionThreshold = 0.0f
            val zPositionThreshold = 1.0f // [px]

            when {
                mOverlap -> {
                    xPositionThreshold = 1.0f
                    yPositionThreshold = 1.0f
                }
                else -> {
                    xPositionThreshold =  mVerticalDividerWidth + 1.0f
                    yPositionThreshold = mHorizontalDividerHeight + 1.0f // [px]
                }
            }



            loop@ (0 until childCount - 1).forEach { i ->
                val child = parent.getChildAt(i)
                val nextChild = parent.getChildAt(i + 1)

                when {
                    child.visibility != View.VISIBLE -> return
                    nextChild.visibility != View.VISIBLE -> return

                    // check if the next item is placed at the bottom or right

                    // check if the next item is placed on the same plane

                    // check if the next item is placed on the same plane
                    else -> {
                        val childBottom = child.bottom + ViewCompat.getTranslationY(child)
                        val nextChildTop = nextChild.top + ViewCompat.getTranslationY(nextChild)
                        val childRight = child.right + ViewCompat.getTranslationX(child)
                        val nextChildLeft = nextChild.left + ViewCompat.getTranslationX(nextChild)

                        when {
                            !(mHorizontalDividerHeight != 0 && Math.abs(nextChildTop - childBottom) < yPositionThreshold || mVerticalDividerWidth != 0 && Math.abs(nextChildLeft - childRight) < xPositionThreshold) -> return@forEach
                        }

                        // check if the next item is placed on the same plane

                        // check if the next item is placed on the same plane
                        val childZ = ViewCompat.getTranslationZ(child) + ViewCompat.getElevation(child)
                        val nextChildZ = ViewCompat.getTranslationZ(nextChild) + ViewCompat.getElevation(nextChild)

                        when {
                            Math.abs(nextChildZ - childZ) >= zPositionThreshold -> return@forEach
                            else -> {
                                val childAlpha = ViewCompat.getAlpha(child)
                                val nextChildAlpha = ViewCompat.getAlpha(nextChild)

                                val tx = (ViewCompat.getTranslationX(child) + 0.5f).toInt()
                                val ty = (ViewCompat.getTranslationY(child) + 0.5f).toInt()

                                when {
                                    mHorizontalDividerHeight != 0 -> {
                                        val left = child.left
                                        val right = child.right
                                        val top = child.bottom - if (mOverlap) mHorizontalDividerHeight else 0
                                        val bottom = top + mHorizontalDividerHeight

                                        mHorizontalDrawable!!.alpha = (0.5f * 255 * (childAlpha + nextChildAlpha) + 0.5f).toInt()
                                        mHorizontalDrawable.setBounds(left + tx,top + ty,right + tx,bottom + ty)
                                        mHorizontalDrawable.draw(c)
                                    }
                                }

                                when {
                                    mVerticalDividerWidth != 0 -> {
                                        val left = child.right - if (mOverlap) mVerticalDividerWidth else 0
                                        val right = left + mVerticalDividerWidth
                                        val top = child.top
                                        val bottom = child.bottom

                                        mVerticalDrawable!!.alpha = (0.5f * 255 * (childAlpha + nextChildAlpha) + 0.5f).toInt()
                                        mVerticalDrawable.setBounds(left + tx,top + ty,right + tx,bottom + ty)
                                        mVerticalDrawable.draw(c)
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    override fun getItemOffsets(outRect: Rect,view: View,parent: RecyclerView,state: RecyclerView.State) {
        if (mOverlap) {
            outRect.set(0,0,0,0)
        } else {
            outRect.set(0,0,mVerticalDividerWidth,mHorizontalDividerHeight)
        }
    }
}
