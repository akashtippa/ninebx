package com.ninebx.ui.signIn.model

import io.realm.RealmObject

/***
 * Created by TechnoBlogger on 19/12/17.
 */

data class UserCredentials( var strUserName: String = "",
                            var strPassword: String = "" ) : RealmObject() {

}
