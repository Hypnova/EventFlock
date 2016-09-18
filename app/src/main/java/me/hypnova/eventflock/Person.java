package me.hypnova.eventflock;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atharva on 2016-09-17.
 */

public class Person {

    private String name;
    private String about;
    private String Uid;
    private List<Person> liked, disliked, neutral;
    private Uri profilePic;

    @Override
    public boolean equals (Object o) {
        Person p = (Person) o;
        return p.getUid().equals (this.getUid());
    }

    public Person(String name, String about, String Uid, Uri profilePic) {
        this.name = name;
        this.about = about;
        this.Uid = Uid;
        this.profilePic = profilePic;
        this.liked = new ArrayList <Person>();
        this.disliked = new ArrayList<Person>();
        this.neutral = new ArrayList<Person>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getUid() { return Uid; }

    public void addLiked(Person p) {
        this.liked.add(p);
    }

    public void addDisliked(Person p){
        this.disliked.add(p);
    }

    public void removeNeutral(Person p) {
        this.neutral.remove(p);
    }

    public void addNeutral(Person p) {
        this.neutral.add(p);
    }

    public Uri getProfilePic() {
        return profilePic;
    }

    public List<Person> getLiked() {
        return liked;
    }

    public List<Person> getDisliked() {
        return disliked;
    }

    public List<Person> getNeutral() {
        return neutral;
    }
}