package com.ninebx.ui.home.baseSubCategories

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Parcelable
import com.ninebx.NineBxApplication
import com.ninebx.ui.base.realm.decrypted.DecryptedCombine
import com.ninebx.ui.base.realm.decrypted.DecryptedEducation
import com.ninebx.ui.base.realm.decrypted.DecryptedWork
import com.ninebx.ui.base.realm.home.education.CombineEducation
import com.ninebx.ui.base.realm.home.homeBanking.Combine
import com.ninebx.utility.*
import io.realm.CombineEducationRealmProxy
import io.realm.Realm
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Sourav on 19-03-2018.
 */
class EducationAndWorkHelper(var category_name : String,
                             val categoryID: String,
                             var classType : String?,
                             var selectedDocument : Parcelable?,
                             val categoryView : Level2CategoryView) {

    private var decryptedEducation: DecryptedEducation? = null
    private var decryptedWork: DecryptedWork? = null

    fun initialize() {
        if (selectedDocument == null) {
            return
        }
        when (classType) {
            DecryptedEducation::class.java.simpleName -> {
                decryptedEducation = selectedDocument as DecryptedEducation
            }
            DecryptedWork::class.java.simpleName -> {
                decryptedWork = selectedDocument as DecryptedWork
            }
        }
    }

    fun getFormForCategory() {
        when (category_name) {
            "Education" -> {
                getEducation()
            }
            "Work" -> {
                getWork()
            }
        }
    }

    private fun getWork() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedWork == null) decryptedWork = DecryptedWork()
        var categoryIndex = 2001
        var category_id = "edu_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name", "Name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("From", "From", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("To", "To", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Currently working here", "", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)

        categoryIndex += 2033
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    private fun getEducation() {
        val categoryList = ArrayList<Level2Category>()
        if (decryptedEducation == null) decryptedEducation = DecryptedEducation()
        var categoryIndex = 1001
        var category_id = "edu_" + categoryIndex
        var category = Level2Category(category_id)
        category.title = "Details"
        category.subCategories.add(Level2SubCategory("Name", "Name", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("Location", "Location", "", Constants.LEVEL2_LOCATION))
        category.subCategories.add(Level2SubCategory("Concenteration/majaor", "Concenteration/majaor", "", Constants.LEVEL2_NORMAL))
        category.subCategories.add(Level2SubCategory("From", "From", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("To", "To", "", Constants.LEVEL2_PICKER))
        category.subCategories.add(Level2SubCategory("Currently studying here", "", "", Constants.LEVEL2_SWITCH))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Notes"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_NOTES))
        categoryList.add(category)

        categoryIndex += 2032
        category_id = "account_details" + categoryIndex
        category = Level2Category(category_id)
        category.title = "Attachments"
        category.subCategories.add(Level2SubCategory("", "", "", Constants.LEVEL2_ATTACHMENTS))
        categoryList.add(category)


        categoryView.onSuccess(categoryList)
    }

    fun setValue(level2Category: Level2SubCategory) {
        when (category_name) {
            "Education" -> {
                setEducation(level2Category)
            }
            "Work" -> {
                setWork(level2Category)
            }
        }
    }

    private fun setWork(level2Category: Level2SubCategory) {
        when (level2Category.title) {
            "Company name" -> decryptedWork!!.companyName= level2Category.titleValue
            "Position" -> decryptedWork!!.position= level2Category.titleValue
            "Name" -> decryptedWork!!.name= level2Category.titleValue
            "Location" -> decryptedWork!!.location= level2Category.titleValue
            "From" -> decryptedWork!!.from= level2Category.titleValue
            "To" -> decryptedWork!!.to= level2Category.titleValue
            else -> {
                when (level2Category.type) {
                    Constants.LEVEL2_NOTES -> decryptedWork!!.notes = level2Category.titleValue
                    Constants.LEVEL2_ATTACHMENTS -> decryptedWork!!.attachmentNames = level2Category.titleValue
                }
            }
        }
    }

        private fun setEducation(level2Category: Level2SubCategory) {
            when (level2Category.title) {

                "Institution name" -> decryptedEducation!!.institutionName = level2Category.titleValue
                "Account name" -> decryptedEducation!!.accountName = level2Category.titleValue
                "Account type" -> decryptedEducation!!.accountType = level2Category.titleValue
                "Name(s) on account" -> decryptedEducation!!.nameOnAccount = level2Category.titleValue
                "Location" -> decryptedEducation!!.location = level2Category.titleValue
                "SWIFT/other code" -> decryptedEducation!!.swiftCode = level2Category.titleValue
                "ABA routing number" -> decryptedEducation!!.abaRoutingNumber = level2Category.titleValue
                "Contacts" -> decryptedEducation!!.contacts = level2Category.titleValue
                "Account number" -> decryptedEducation!!.accountNumber = level2Category.titleValue
                "Website" -> decryptedEducation!!.website = level2Category.titleValue
                "Contacts" -> decryptedEducation!!.contacts = level2Category.titleValue
                "Username/login" -> decryptedEducation!!.userName = level2Category.titleValue
                "Password" -> decryptedEducation!!.password = level2Category.titleValue
                "PIN" -> decryptedEducation!!.pin = level2Category.titleValue
                "Payment method on file" -> decryptedEducation!!.paymentMethodOnFile = level2Category.titleValue
                "Notes" -> decryptedEducation!!.notes = level2Category.titleValue
                "Title" -> decryptedEducation!!.title = level2Category.titleValue
                else -> {
                    when (level2Category.type) {
                        Constants.LEVEL2_NOTES -> decryptedEducation!!.notes = level2Category.titleValue
                        Constants.LEVEL2_ATTACHMENTS -> decryptedEducation!!.attachmentNames = level2Category.titleValue
                    }
                }
                }
        }
    private var mCombine : Parcelable ?= null
    @SuppressLint("StaticFieldLeak")
    fun saveDocument(context: Context, combineItem: Parcelable?, title: String, subTitle: String){
        mCombine = combineItem
        val currentUsers = NineBxApplication.getPreferences().userFirstName + " " + NineBxApplication.getPreferences().userLastName
        val sdf = SimpleDateFormat(" E,MMM dd,yyyy, HH:mm")
        val currentDateandTime = sdf.format(Date())
        if (decryptedEducation != null) {
            decryptedEducation!!.selectionType = categoryID
            decryptedEducation!!.institutionName = title
            decryptedEducation!!.accountName = subTitle
            AppLogger.d("SelectionType ", "decryptedEducation" + decryptedEducation!!.selectionType)
            if (decryptedEducation!!.created.isEmpty())
                decryptedEducation!!.created = currentUsers + " " + currentDateandTime
            decryptedEducation!!.modified = currentUsers + " " + currentDateandTime

            var isSaveComplete = false
            if (decryptedEducation!!.id.toInt() == 0) {
                decryptedEducation!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedEducation!!.id)
            }

            AppLogger.d("saveDocument", "Document Id " + decryptedEducation!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedEducation!!)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val education = encryptEducation(decryptedEducation!!)
                            realm.insertOrUpdate(education)
                            AppLogger.d("CombineLevel2 ", "Inserted ")
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm(mCombine!!)
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(CombineEducation::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineEducation::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptEducation(decryptedEducation!!)
                            if (combineRealm!!.educationItems.contains(encryptedObject)) {
                                val index = combineRealm!!.educationItems.indexOf(encryptedObject)
                                if (index != -1) {
                                    combineRealm!!.educationItems[index] = (encryptedObject)
                                }
                            } else {
                                combineRealm!!.educationItems.add(encryptedObject)
                            }
                            /*combine.financialItems.add( decryptedEducation )
                            val encryptedCombine = encryptCombine(combine)*/
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm(mCombine!!)
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }
        if (decryptedWork != null) {
            decryptedWork!!.selectionType = categoryID//check from IOS
            decryptedWork!!.companyName = title
            AppLogger.d("SelectionType ", "decryptedEducation" + decryptedWork!!.selectionType)
            if (decryptedEducation!!.created.isEmpty())
                decryptedEducation!!.created = currentUsers + " " + currentDateandTime
            decryptedEducation!!.modified = currentUsers + " " + currentDateandTime

            var isSaveComplete = false
            if (decryptedEducation!!.id.toInt() == 0) {
                decryptedEducation!!.id = getUniqueId()
                AppLogger.d("saveDocument", "id" + decryptedWork!!.id)
            }

            AppLogger.d("saveDocument", "Document Id " + decryptedWork!!.id)
            AppLogger.d("saveDocument", "Document : " + decryptedWork!!)
            object : AsyncTask<Void, Void, Unit>() {
                override fun doInBackground(vararg p0: Void?) {
                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            realm!!.beginTransaction()
                            val work = encryptWork(decryptedWork!!)
                            realm.insertOrUpdate(work)
                            AppLogger.d("CombineLevel2 ", "Inserted ")
                            realm!!.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm(mCombine!!)
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)

            object : AsyncTask<Void, Void, Unit>() {

                override fun doInBackground(vararg p0: Void?) {

                    prepareRealmConnections(context, false, Constants.REALM_END_POINT_COMBINE, object : Realm.Callback() {
                        override fun onSuccess(realm: Realm?) {
                            val combine: DecryptedCombine = mCombine as DecryptedCombine
                            AppLogger.d("saveDocument", "Combine Id " + combine!!.id)
                            var combineRealm = realm!!.where(CombineEducation::class.java).equalTo("id", combine.id).findFirst()
                            realm.beginTransaction()
                            if (combineRealm == null) {
                                combineRealm = realm.createObject(CombineEducation::class.java, getUniqueId())
                            }
                            val encryptedObject = encryptWork(decryptedWork!!)
                            if (combineRealm!!.workItems.contains(encryptedObject)) {
                                val index = combineRealm!!.workItems.indexOf(encryptedObject)
                                if (index != -1) {
                                    combineRealm!!.workItems[index] = (encryptedObject)
                                }
                            } else {
                                combineRealm!!.workItems.add(encryptedObject)
                            }
                            /*combine.financialItems.add( decryptedEducation )
                            val encryptedCombine = encryptCombine(combine)*/
                            realm.insertOrUpdate(combineRealm)
                            realm.commitTransaction()
                        }
                    })
                }

                override fun onPostExecute(result: Unit?) {
                    super.onPostExecute(result)
                    if (isSaveComplete) {
                        isSaveComplete = true
                    } else {
                        categoryView.savedToRealm(mCombine!!)
                    }
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
        }



    }

}