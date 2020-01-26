package com.example.copyproject;

public class Eventdetails {

    public String eventname,eventdate,eventlocation,eventtime,eventid;
    private String mImageUrl;

    public Eventdetails() {
    }

    public Eventdetails(String eventname, String eventdate, String eventlocation, String eventtime,String mImageUrl,String eventid) {
        this.eventname = eventname;
        this.eventdate = eventdate;
        this.eventlocation = eventlocation;
        this.eventtime = eventtime;
        this.mImageUrl = mImageUrl;
        this.eventid = eventid;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }

    public String getEventlocation() {
        return eventlocation;
    }

    public void setEventlocation(String eventlocation) {
        this.eventlocation = eventlocation;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }
}
