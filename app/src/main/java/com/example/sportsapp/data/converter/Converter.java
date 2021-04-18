/*
 * *
 *  * Created by Cihat Mert Baykal
 *  * Copyright (c) Ikol Mobile. All rights reserved.
 *  * Last modified 3/1/21 2:58 PM
 *
 */

package com.example.sportsapp.data.converter;

import androidx.room.TypeConverter;

import com.example.sportsapp.data.model.league.LeagueModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {

    @TypeConverter
    public static List<LeagueModel> stringToList(String value) {
        Type listType = new TypeToken<List<LeagueModel>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String listToString(List<LeagueModel> value) {
        return new Gson().toJson(value);
    }

}
