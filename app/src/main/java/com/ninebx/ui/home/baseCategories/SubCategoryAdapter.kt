package com.ninebx.ui.home.baseCategories

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ninebx.R
import com.ninebx.ui.base.ActionClickListener
import com.ninebx.utility.Constants

/**
 * Created by Alok on 12/01/18.
 */
class SubCategoryAdapter( val subCategories : ArrayList<SubCategory>, val actionClickListener: ActionClickListener ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent!!.context)
        return when( viewType ) {
            Constants.SUB_CATEGORY_ADD_ITEM -> {
               ItemViewHolder(layoutInflater.inflate(R.layout.item_sub_category_add, parent, false))
            }
            Constants.SUB_CATEGORY_ADD_PERSON -> {
                PersonViewHolder(layoutInflater.inflate(R.layout.item_sub_category_add_person, parent, false))
            }
            Constants.SUB_CATEGORY_DISPLAY_PERSON -> {
                DisplayViewHolder(layoutInflater.inflate(R.layout.item_sub_category_display_person, parent, false))
            }
            else -> {
                ItemViewHolder(layoutInflater.inflate(R.layout.item_sub_category_add, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return subCategories.size
    }

    override fun getItemViewType(position: Int): Int {
        return subCategories[position].type
    }

    inner class ItemViewHolder( itemView : View? ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val tvSubTitle = itemView!!.findViewById<TextView>(R.id.tvSubTitle)
        val tvCount = itemView!!.findViewById<TextView>(R.id.tvCount)

        override fun onClick(view: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                when( view!!.id ) {
                    R.id.tvCount -> {
                        actionClickListener.onItemClick(position, "add_item")
                    }
                    R.id.tvSubTitle -> {
                        actionClickListener.onItemClick(position, "display")
                    }
                }
            }

        }

        init {
            tvSubTitle.setOnClickListener(this)
            tvCount.setOnClickListener(this)
        }

    }

    inner class PersonViewHolder( itemView : View? ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val tvSubTitle = itemView!!.findViewById<TextView>(R.id.tvSubTitle)

        override fun onClick(view: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                when( view!!.id ) {
                    R.id.tvSubTitle -> {
                        actionClickListener.onItemClick(position, "add_person")
                    }
                }
            }

        }

        init {
            tvSubTitle.setOnClickListener(this)
        }

    }

    inner class DisplayViewHolder( itemView : View? ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val tvSubTitle = itemView!!.findViewById<TextView>(R.id.tvSubTitle)

        override fun onClick(view: View?) {
            val position = adapterPosition
            if( position != RecyclerView.NO_POSITION ) {
                when( view!!.id ) {
                    R.id.tvSubTitle -> {
                        actionClickListener.onItemClick(position, "display_person")
                    }
                }
            }

        }

        init {
            tvSubTitle.setOnClickListener(this)
        }

    }
}