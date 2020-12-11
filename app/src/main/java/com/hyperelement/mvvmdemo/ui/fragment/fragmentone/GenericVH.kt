package com.hyperelement.mvvmdemo.ui.fragment.fragmentone

import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hyperelement.mvvmdemo.R
import com.hyperelement.mvvmdemo.data.datasources.local.EmployeeEntity
import com.hyperelement.mvvmdemo.databinding.ItemSimpleRowBinding
import com.hyperelement.mvvmdemo.utilities.ext.inflate
import smartadapter.SmartRecyclerAdapter
import smartadapter.viewholder.SmartViewHolder

class GenericVH(var parentView: ViewGroup) : SmartViewHolder<EmployeeEntity>(
    parentView.inflate<ItemSimpleRowBinding>(R.layout.item_simple_row).root
) {
    override fun bind(item: EmployeeEntity) {
        val binding = DataBindingUtil.getBinding<ItemSimpleRowBinding>(itemView)
        binding?.dataItem = item
    }
}