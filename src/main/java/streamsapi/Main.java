package streamsapi;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        
        List<Person> people = getPeople();

        //Imperative approach
        System.out.println("Imperative");

        List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (person.getGender().equals(Gender.FEMALE)) {
                females.add(person);
            }
        }
        for (Person person : females) {
            System.out.println(person);
        }

        //Declarative approach
        System.out.println("Declarative");

        //Filter
        System.out.println("Filter");
        List<Person> females2 = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .toList();
        females2.forEach(System.out::println);

        //Sort
        System.out.println("Sort");
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed())
                .toList();
        sorted.forEach(System.out::println);

        //All match (ASK questions)
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 5);
        System.out.println("all match ? " + allMatch);

        //Any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 30);
        System.out.println("any match ? " + anyMatch);

        //none match
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Ali"));
        System.out.println("noneMatch match ? " + noneMatch);

        //Max ( ASK information of the max value within a collections )
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        //Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        //Group
        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });


        //the name of the oldest female
        Optional<String> oldestFemale = people.stream()
                .filter(person -> person.getGender().equals(Gender.FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemale.ifPresent(System.out::println);

    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Abdelrhman", 22, Gender.MALE),
                new Person("Ali", 40, Gender.MALE),
                new Person("Hassan", 15, Gender.MALE),
                new Person("Hagar", 9, Gender.FEMALE),
                new Person("Alia", 21, Gender.FEMALE),
                new Person("Mohamed", 33, Gender.MALE),
                new Person("Sara", 22, Gender.FEMALE),
                new Person("Ziad", 11, Gender.MALE)
        );
    }
}
