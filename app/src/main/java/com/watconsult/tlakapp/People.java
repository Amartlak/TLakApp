package com.watconsult.tlakapp;


import android.os.Parcel;
import android.os.Parcelable;

public class People implements Parcelable {
    private String people_name;
    private int people_id;
    private int occupied;

    // Constructor
    public People(String people_name,int people_id, int occupied){
        this.people_name = people_name;
        this.people_id = people_id;
        this.occupied = occupied;
    }
    // Getter and setter methods
    public String getPeople_name() {
        return people_name;
    }

    public void setPeople_name(String people_name) {
        this.people_name = people_name;
    }
    public Integer getPeople_id() {
        return people_id;
    }

    public void setPeople_id(int people_id) {
        this.people_id = people_id;
    }
    public Integer getOccupied() {
        return occupied;
    }

    public void setOccupied(int occupied) {
        this.occupied = occupied;
    }
    // Parcelling part
    public People(Parcel in){

        String[] data = new String[3];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.people_name = data[0];
        this.people_id = Integer.parseInt(data[1]);
        this.occupied = Integer.parseInt(data[2]);
    }

  /*  @Оverride
    public int describeContents(){
        return 0;
    }*/

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.people_name,
                String.valueOf(this.people_id),
                String.valueOf(this.occupied)});
    }
    public static final Creator CREATOR = new Creator() {
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        public People[] newArray(int size) {
            return new People[size];
        }
    };
}