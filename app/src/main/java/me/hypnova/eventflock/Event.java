package me.hypnova.eventflock;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Atharva on 2016-09-17.
 */
public class Event {
    private String name;
    private String description;
    private String code;
    private String location;
    private Calendar time;
    private List<Person> people;
    private List<Person> admins;

    public Event(String name, String description, String location, Calendar time) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.time = time;
        this.people = new ArrayList<Person>();
        this.admins = new ArrayList<Person>();
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    public List<Person> getPeople() {
        return people;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) { this.code = code; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addPerson(Person p) {
        this.people.add(p);
    }

    public void removePerson(Person p) {
        this.people.remove(p);
    }

    public List<Person> getAdmin (){
        return admins;
    }

    public void addAdmin(Person p) {
        this.admins.add(p);
    }

    public void removeAdmin(Person p) {
        this.admins.remove(p);
    }
}
