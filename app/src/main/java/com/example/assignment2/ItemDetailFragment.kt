package com.example.assignment2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ItemDetailFragment : Fragment() {

    companion object {
        private const val ARG_ITEM = "item"

        fun newInstance(item: String): ItemDetailFragment {
            val fragment = ItemDetailFragment()
            val args = Bundle()
            args.putString(ARG_ITEM, item)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_item_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemDetailTextView: TextView = view.findViewById(R.id.itemDetailTextView)
        arguments?.getString(ARG_ITEM)?.let {
            itemDetailTextView.text = it
        }
    }
}
