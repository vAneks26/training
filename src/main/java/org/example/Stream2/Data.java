package org.example.Stream2;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private static List<Person> person = new ArrayList<>();

    static {
        Person person1 =new Person("иван","шлыков",25);
        Person person2 =new Person("вася","петров",27);
        Person person3 =new Person("валера","сурин",25);
        Person person4 =new Person("паша","ступ",24);
        Person person5 =new Person("илья","уее",21);
        Person person6 =new Person("дима","укееку",23);
        Person person7 =new Person("миша","куеуне",19);
        Person person8 =new Person("влад","керок",17);
        Person person9 =new Person("юра","кео",16);
        Person person10 =new Person("егор","шленое",19);
        Person person11 =new Person("слава","кр",32);
        Person person12 =new Person("вася","рто",35);
        Person person13 =new Person("иван","опао",34);
        Person person14 =new Person("петя","апо",29);
        Person person15 =new Person("макс","аорко",28);
        Person person16 =new Person("олег","паоо",22);

        person.add(person1);
        person.add(person2);
        person.add(person3);
        person.add(person4);
        person.add(person5);
        person.add(person6);
        person.add(person7);
        person.add(person8);
        person.add(person9);
        person.add(person10);
        person.add(person11);
        person.add(person12);
        person.add(person13);
        person.add(person14);
        person.add(person15);
        person.add(person16);
    }

    public static List<Person> getPerson() {
        return person;
    }

    public static void setPerson(List<Person> person) {
        Data.person = person;
    }
}
