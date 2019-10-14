package com.linkdev.demokotlin.bottomSheetDemo

import android.content.Context
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.linkdev.demokotlin.R
import kotlinx.android.synthetic.main.bottom_sheet_dialog.view.*


class BottomSheetManager(context: Context) : BottomSheetDialog(context) {

    fun showBottomSheet(arrQueryString: Array<String>) {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)
        view.rvFilterItem.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        view.rvFilterItem.adapter = BottomSheetAdapter(context, arrQueryString)
        setContentView(view)

        show()
    }
}