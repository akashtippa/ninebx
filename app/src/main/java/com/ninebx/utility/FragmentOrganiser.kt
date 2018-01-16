package com.ninebx.utility

/***
 * Created by TechnoBlogger on 08/01/18.
 */

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.inputmethod.InputMethodManager
import java.util.*

/***
 * Created by Techno Blogger on 24/10/17.
 */

class FragmentOrganiser(private val _activity: FragmentActivity, private val _id_parent_frame_view: Int) {

    var currentFragmentTag = ""
    private val fragmentManager: FragmentManager = _activity.supportFragmentManager

    private var isPaused = false

    private val _list_instant_state = ArrayList<InstanseState>()
    private val _list_back_state = ArrayList<InstanseState>()

    fun updateFragment(bundle: Bundle, toFragmnt: Fragment) {
        fragmentManager.putFragment(bundle, currentFragmentTag, toFragmnt)

    }

    fun clearBackStack() {
        val fragmentList = fragmentManager.fragments
        if (fragmentList != null) {
            for (f in fragmentList) {
                if (f != null)
                    Log.e("", "f name:" + f.javaClass.name)
            }
        }
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack(null,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
            fragmentManager.beginTransaction().commit()
            _list_instant_state.clear()
            _list_back_state.clear()
            Log.e("",
                    "Count back entitty" + fragmentManager.backStackEntryCount)
        }
    }

    fun popUpFragment(popBackTo: Int) {
        try {
            if (popBackTo > fragmentManager.backStackEntryCount) {
                Log.e("exception",
                        "popBackTo count is grater then back stack count")
                return
            }
            val count = fragmentManager.backStackEntryCount - popBackTo
            val id = fragmentManager.getBackStackEntryAt(count).id
            val name = fragmentManager.getBackStackEntryAt(count)
                    .name
            Log.e(count.toString() + " the fragment is " + id, "" + name)
            Log.e("", "yes poped " + count)
            fragmentManager.popBackStack(count,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun popUpFragment(fragment_tag: String) {
        try {
            if (fragmentManager.backStackEntryCount > 0) {
                // for (int i = 0; i <
                // fragmentManager.getBackStackEntryCount(); i++) {
                for (i in fragmentManager.backStackEntryCount downTo 0) {
                    Log.e("the fragment is", "" + fragmentManager.fragments[i].javaClass.simpleName)
                    try {
                        if (_list_back_state != null && _list_back_state.size > 0) {
                            _list_back_state
                                    .removeAt(_list_back_state.size - 1)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    if (fragmentManager.fragments[i].javaClass
                            .simpleName.equals(fragment_tag, ignoreCase = true)) {
                        fragmentManager.popBackStack()
                        return
                    } else
                        fragmentManager.popBackStack()
                }
                // fragmentManager.popBackStack(fragment_tag,
                // FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        } catch (e: Exception) {
        }

    }

    fun replace(toFragment: Fragment, isAllowBack: Boolean) {
        replace(toFragment, null, isAllowBack)
    }

    @JvmOverloads
    fun replace(toFragment: Fragment?, bundle: Bundle? = null, isAllowBack: Boolean = false) {
        hideKeyboard()
        if (toFragment == null)
            return
        if (!isToAdd(toFragment))
            return

        if (isPaused) {
            _list_instant_state.add(InstanseState(toFragment, bundle!!,
                    isAllowBack, FragmentType.REPLACE))
            return
        }
        if (bundle != null) {
            toFragment.arguments = bundle
        }

        val _fragment_transiction = fragmentManager
                .beginTransaction()

        if (isAllowBack) {
            _fragment_transiction.addToBackStack(currentFragmentTag)
        }

        _fragment_transiction.replace(_id_parent_frame_view, toFragment,
                toFragment.javaClass.name).commit()

        currentFragmentTag = toFragment.javaClass.name
        //clearBackStack();
    }

    fun add(toFragment: Fragment, isAllowBack: Boolean) {
        add(toFragment, null, isAllowBack)
    }

    @JvmOverloads
    fun add(toFragment: Fragment?, bundle: Bundle? = null, isAllowBack: Boolean = false) {
        hideKeyboard()
        if (toFragment == null)
            return
        if (toFragment.javaClass.simpleName
                .equals("FragmentUserHomeMyOrder", ignoreCase = true)) {
            if (!isToAdd(toFragment)) {
                try {
                    Log.e("Current Fragment", "" + toFragment.javaClass.simpleName)
                    if (toFragment.javaClass.simpleName
                            .equals("FragmentUserHomeMyOrder", ignoreCase = true)) {
                        popUpFragment(toFragment.javaClass.simpleName)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                return
            }
        }
        if (isPaused) {
            _list_instant_state.add(InstanseState(toFragment, bundle!!,
                    isAllowBack, FragmentType.ADD))
            return
        }

        if (bundle != null) {
            toFragment.arguments = bundle
        }

        val _fragment_transiction = fragmentManager
                .beginTransaction()
        if (isAllowBack) {
            _fragment_transiction.addToBackStack(currentFragmentTag)
            _list_back_state.add(InstanseState(toFragment, bundle!!,
                    isAllowBack, FragmentType.ADD))
        }
        _fragment_transiction.add(_id_parent_frame_view, toFragment,
                toFragment.javaClass.name).commit()
        currentFragmentTag = toFragment.javaClass.name
    }

    private fun isToAdd(toFragment: Fragment): Boolean {
        if (_list_back_state == null)
            return true
        for (i in _list_back_state.indices) {
            val instanseState = _list_back_state[i]
            val _fragment = instanseState.fragment ?: break
            val name1 = toFragment.javaClass.name
            val name2 = _fragment.javaClass.name
            if (name1.equals(name2, ignoreCase = true))
                return false
        }
        return true
    }

    fun isToAdd(fragmentManager: FragmentManager, fragment: Fragment?): Boolean {
        if (fragment == null)
            return false
        val _list_fragment = fragmentManager.fragments ?: return true
        for (_fragment in _list_fragment) {
            if (_fragment == null)
                break
            if (fragment.javaClass.name
                    .equals(_fragment.javaClass.name, ignoreCase = true))
                return false

        }
        return true
    }

    fun hasNoMoreBack(): Boolean {
        return fragmentManager.backStackEntryCount == 0
    }

    fun onBackPress(back: String?) {
        try {
            if (_list_back_state != null && _list_back_state.size > 0) {
                _list_back_state.removeAt(_list_back_state.size - 1)
                if (_list_back_state != null && _list_back_state.size > 0) {
                    val instanseState = _list_back_state[_list_back_state.size - 1]
                    currentFragmentTag = instanseState.fragment!!.javaClass
                            .name
                } else if (back != null)
                    currentFragmentTag = back
            } else {
                if (back != null)
                    currentFragmentTag = back
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun onPause() {
        isPaused = true
    }

    fun onResume() {
        isPaused = false
        if (_list_instant_state != null) {
            Log.e("", "size to exc:" + _list_instant_state.size)
            for (i in _list_instant_state.indices) {
                val instanseState = _list_instant_state[i]
                when (instanseState._fragment_type) {
                    FragmentOrganiser.FragmentType.ADD -> add(instanseState.fragment, instanseState.bundle,
                            instanseState.isToBack)
                    FragmentOrganiser.FragmentType.REPLACE -> replace(instanseState.fragment, instanseState.bundle,
                            instanseState.isToBack)
                    else -> {
                    }
                }
            }
            _list_instant_state.clear()
        }
    }

    enum class FragmentType {
        ADD, REPLACE
    }

    private fun hideKeyboard() {
        try {
            val inputManager = _activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(_activity.currentFocus!!
                    .windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
        }

    }

    inner class InstanseState(var fragment: Fragment?, var bundle: Bundle,
                              var isToBack: Boolean, var _fragment_type: FragmentType)

}