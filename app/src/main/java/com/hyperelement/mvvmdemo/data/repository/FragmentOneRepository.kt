package com.hyperelement.mvvmdemo.data.repository

import android.content.Context
import com.hyperelement.mvvmdemo.data.datasources.local.EmployeeEntity

//we can inject context or dao as per the necessary
class FragmentOneRepository(
    private val context: Context
) {
    fun getEmployee(): List<EmployeeEntity> {
        val mEmployeeList = mutableListOf<EmployeeEntity>()
        for (x in 1..100) {
            mEmployeeList.add(
                EmployeeEntity(x, "Emp $x")
            )
        }
        return mEmployeeList.shuffled()
    }
}