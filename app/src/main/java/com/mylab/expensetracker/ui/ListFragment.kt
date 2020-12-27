package com.mylab.expensetracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mylab.expensetracker.R
import com.mylab.expensetracker.datamodel.Expense
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {
    private val listAdapter = ExpenseListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val listViewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        listViewModel.readAllExpense.observe(viewLifecycleOwner) { expenseList ->

            getRecycler(expenseList)

        }
        listAdapter.itemClick = { expense ->

            findNavController().navigate(ListFragmentDirections.actionListFragmentToEditDeleteFragment(expense))
        }



    }

    private fun getRecycler(it: List<Expense>?) {

        recycler_view.adapter = listAdapter
        listAdapter.submitList(it)
    }





}