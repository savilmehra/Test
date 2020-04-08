
package com.in.test.entities;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test implements Serializable
{

    @SerializedName("diet_duration")
    @Expose
    private Integer dietDuration;
    @SerializedName("week_diet_data")
    @Expose
    private WeekDietData weekDietData;
    private final static long serialVersionUID = -8084778957253464516L;

    public Integer getDietDuration() {
        return dietDuration;
    }

    public void setDietDuration(Integer dietDuration) {
        this.dietDuration = dietDuration;
    }

    public WeekDietData getWeekDietData() {
        return weekDietData;
    }

    public void setWeekDietData(WeekDietData weekDietData) {
        this.weekDietData = weekDietData;
    }

}
