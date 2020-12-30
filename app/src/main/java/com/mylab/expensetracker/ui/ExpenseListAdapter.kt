package com.mylab.expensetracker.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.Expense
import com.mylab.expensetracker.util.Util
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import kotlinx.android.synthetic.main.list_element.view.*


class ExpenseListAdapter :
    ListAdapter<Expense, ExpenseListAdapter.ExpenseViewHolder>(ExpenseDiffUtil()) {

    var itemClick: ((Expense) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_element, parent, false)
        return ExpenseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item = currentList[position]
        holder.itemView.element_text_title.text = item.title
        holder.itemView.element_text_amount.text = item.amount.toBigDecimal().toPlainString()
        holder.itemView.element_text_description.text = item.description
        holder.itemView.element_text_date.text = Util.convertedTime(item.date)
        if (item.amountType == 1) {
            holder.itemView.circle_image.setImageResource(R.drawable.income)
        } else {
            holder.itemView.circle_image.setImageResource(R.drawable.expense)
        }

    }

    class ExpenseDiffUtil : DiffUtil.ItemCallback<Expense>() {
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            return oldItem == newItem
        }

    }

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                itemClick?.invoke(currentList[adapterPosition])
            }

        }
    }

}



