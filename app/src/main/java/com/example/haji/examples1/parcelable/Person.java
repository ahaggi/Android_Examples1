package com.example.haji.examples1.parcelable;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by haji on 1/24/18.
 */

public class Person implements Parcelable{


    private String navn;
    private Uri uri;

    public Person(String n, String u){
        navn = n;
        uri = Uri.parse(u);
    }

    protected Person(Parcel in) {
        navn = in.readString();
        uri = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getNavn(){return navn;}

    public void setNavn(String n){navn = n;}

    public Uri getUri(){return uri;}

    public void setUri(Uri u){uri =u;}

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(navn);
        dest.writeParcelable(uri, flags);
    }
}
