
package com.in.test.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Monday implements Serializable
{

    @SerializedName("food")
    @Expose
    private String food;
    @SerializedName("meal_time")
    @Expose
    private String mealTime;
    private final static long serialVersionUID = -1425388389666269474L;

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String mealTime) {
        this.mealTime = mealTime;
    }

}
