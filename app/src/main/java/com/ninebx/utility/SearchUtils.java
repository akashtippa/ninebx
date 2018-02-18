package com.ninebx.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ninebx.ui.base.realm.decrypted.TestSearch;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Alok on 16/02/18.
 */

public class SearchUtils {

    public static boolean searchString( Object classObject, String search ) {
        boolean isAvailable = false;
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(classObject);
        Map<String,Object> result = new Gson().fromJson(json, Map.class);
        AppLogger.INSTANCE.d("SearchUtils", result.toString());
        for( Object searchValue : result.values() ) {
            if( searchValue instanceof String && searchValue.toString().toLowerCase().contains(search.toLowerCase()) ) {
                isAvailable = true;
                break;
            }
        }
        return isAvailable;
    }
    
    public static void search() {
        ArrayList<TestSearch> TestSearchs = new ArrayList<TestSearch>();
        int category = 0;
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        TestSearchs.add( new TestSearch( category++, "Level3" + category ));
        /*for( TestSearch testSearch : TestSearchs ) {
            AppLogger.INSTANCE("Searching", searchString(testSearch, "level"));
        }*/
    }
}
