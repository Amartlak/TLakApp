package com.watconsult.tlakapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Peoples implements Parcelable {

  /*  private int peo;
    private String name;

    private ArrayList<String> address;*/

    private String people_name;
    private int people_id;
    private int occupied;

    public Peoples(String people_name, int people_id, int occupied) {
        this.people_name = people_name;
        this.people_id = people_id;
        this.occupied = occupied;
    }
    /*public Peoples(String name, int age, ArrayList<String> address) {
        this.name = name;
        this.age = age;
        this.address = address;

    }*/

    public Peoples(Parcel source) {
        people_name = source.readString();
        people_id = source.readInt();
        occupied = source.readInt();
    }

    public Peoples(String people_name, String people_name1, String occupied) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(people_name);
        dest.writeInt(people_id);
        dest.writeInt(occupied);
    }

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

  /*  public ArrayList<String> getAddress() {
        if (!(address == null))
            return address;
        else
            return new ArrayList<String>();
    }*/

    public static final Creator<Peoples> CREATOR = new Parcelable.Creator<Peoples>() {
        @Override
        public Peoples[] newArray(int size) {
            return new Peoples[size];
        }

        @Override
        public Peoples createFromParcel(Parcel source) {
            return new Peoples(source);
        }
    };
}