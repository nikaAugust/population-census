package homework;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        Stream<Person> streamAge = persons.stream();
        long underage = streamAge.filter(w -> w.getAge() < 18).count();


        Stream<Person> streamConscript = persons.stream();
        List<String> conscript = streamConscript
                .filter(w -> w.getAge() >= 18 && w.getAge() <= 27 && w.getSex() == Sex.MAN)
                .map(Person::getFamily).collect(Collectors.toList());


        Stream<Person> workable = persons.stream();
        List<Person> educatedWorkers = workable
                .filter(w -> w.getAge() >= 18 && w.getAge() <= (w.getSex() != Sex.MAN ? 60 : 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
    }
}
