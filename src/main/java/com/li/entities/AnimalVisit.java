package com.li.entities;

public class AnimalVisit {
    private String animalName;

    public int getAnimalVisits() {
        return animalVisits;
    }

    private int animalVisits;
    private String weekDay;

    public String getAnimalName() {

        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalVisits(int animalVisits) {
        this.animalVisits = animalVisits;
    }


    public String getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(String weekDay) {
        this.weekDay = weekDay;
    }
}
