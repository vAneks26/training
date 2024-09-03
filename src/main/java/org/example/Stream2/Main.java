package org.example.Stream2;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> person = Data.getPerson();

        List<Person> person2 = person.stream()
                .filter(p -> p.getAge() > 28 )
                .sorted()
                .map(p -> new Person(p.getFirstName(),"Иванов", p.getAge()))
                .collect(Collectors.toList());


//        Optional<Person> person3 = person.stream()
//                .filter(p -> p.getAge() > 28 )
////              .findFirst()
//                .findAny();   //искать любого
//        System.out.println(person3.get());

//        long count = person.stream()
//                .filter(p -> p.getAge() > 28 )
//                .count();//кол-во записей
//        System.out.println(count);

//        for(int i=0; i< person2.size();i++){
//            System.out.println(person2.get(i));
//        }
        person2.forEach(p-> System.out.println(p));
    }
}
