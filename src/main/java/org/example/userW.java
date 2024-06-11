package org.example;

public class userW {
    private String name;
    private String date;
    private String description;

    public userW(String name, String date, String description) {
        this.name = name;

        this.date = date;
        this.description = description;


    }public String getInfo(){
      return getName()+"        "+getDate()+"            "+getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
