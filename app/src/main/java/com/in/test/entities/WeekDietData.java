
package com.in.test.entities;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeekDietData implements Serializable
{

    @SerializedName("thursday")
    @Expose
    private List<Thursday> thursday = null;
    @SerializedName("wednesday")
    @Expose
    private List<Wednesday> wednesday = null;
    @SerializedName("monday")
    @Expose
    private List<Monday> monday = null;
    private final static long serialVersionUID = 7562232429795222398L;

    public List<Thursday> getThursday() {
        return thursday;
    }

    public void setThursday(List<Thursday> thursday) {
        this.thursday = thursday;
    }

    public List<Wednesday> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<Wednesday> wednesday) {
        this.wednesday = wednesday;
    }

    public List<Monday> getMonday() {
        return monday;
    }

    public void setMonday(List<Monday> monday) {
        this.monday = monday;
    }

}
