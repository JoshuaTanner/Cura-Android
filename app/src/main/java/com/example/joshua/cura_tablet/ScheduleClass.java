package com.example.joshua.cura_tablet;

public class ScheduleClass {

    private int id;
    private String name;
    private String start_time;
    private String end_time;
    private String description;

    public ScheduleClass()  { }

    public ScheduleClass(int id,String name, String start_time, String end_time, String description)
    {
        this.id=id;
        this.name=name;
        this.description=description;
        this.start_time = start_time;
        this.end_time = end_time;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }
    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
    public void setDescription(String address) {
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getStart_time() {
        return start_time;
    }
    public String getEnd_time() {
        return end_time;
    }
    public String getDescription() {
        return description;
    }
}