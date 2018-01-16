package com.ninebx.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ninebx.R

/**
 * Created by Alok on 15/01/18.
 */
class InvitePeopleFragment : BaseAuthFragment() {

    override fun validate(): Boolean {
        return true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_invite_people, container, false)
    }


}