import java.util.*;
import java.util.stream.Collectors;

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
        long numberOfPeopleUnderTheAgeOf18 = persons.parallelStream().filter(s -> s.getAge() < 18).count();
        System.out.println(numberOfPeopleUnderTheAgeOf18);
        List<String> armyPerson = persons.parallelStream()
                .filter(s -> s.getAge() >= 18 && s.getAge() <= 57).map(s -> s.getFamily()).collect(Collectors.toList());

        List<String> worker = persons.parallelStream()
                .filter(s -> s.getSex().equals(Sex.MAN) && s.getAge() >= 18 && s.getEducation().equals(Education.HIGHER) && s.getAge() <= 65
                        || s.getSex().equals(Sex.WOMAN) && s.getAge() >= 18 && s.getEducation().equals(Education.HIGHER) && s.getAge() <= 65)
                .map(s -> s.getFamily())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

    }
}