import java.time.LocalDate;
import java.util.*;
import java.util.function.Supplier;

import method_reference.ComparisonProvider;
import method_reference.Personn;

public class Main {
    public static void main(String[] args) {
        Personn[] pArr = new Personn[]{
                new Personn("003", LocalDate.of(2016,9,1)),
                new Personn("001", LocalDate.of(2016,2,1)),
                new Personn("002", LocalDate.of(2016,3,1)),
                new Personn("004", LocalDate.of(2016,12,1))};
        //静态方法引用
        //        Arrays.sort(pArr, Personn::compareByAge);
        //已实例化的方法引用
        ComparisonProvider provider = new ComparisonProvider();
        Arrays.sort(pArr, provider::compareByAge);
        System.out.println(Arrays.asList(pArr));
        //同属一个类的实例方法引用
        String[] stringArray = { "Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda" };
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        System.out.println(Arrays.asList(stringArray));
        //构造方法引用
        Set<Personn> rosterSet = transferElements(Arrays.asList(pArr), HashSet::new);
        rosterSet.forEach(System.out::println);


    }

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>>
    DEST transferElements(
            SOURCE sourceCollection,
            Supplier<DEST> collectionFactory) {

        DEST result = collectionFactory.get();
        for (T t : sourceCollection) {
            result.add(t);
        }
        return result;
    }

    private static void lambda(){
        List<Person> personList = Arrays.asList(
                new Person("zhang", "san", 25),
                new Person("li", "si", 30),
                new Person("wang", "wu", 29)
        );
        personList.stream().filter(p -> p.getFirstName().startsWith("z")).forEach(System.out::println);
    }

    private String Optional(){
        Person person = null;
        Optional<Person> personOptional = Optional.ofNullable(person);
        personOptional.ifPresent(System.out::println);
//        return  personOptional.orElse(person);
//        return personOptional.orElseGet(()->new Person("3","2",1));
        return personOptional.map(Person::getFirstName).map(name->name.toLowerCase()).orElse(null);
    }


}
