import it.sam.be.entities.User;
import it.sam.be.functionalinterface.StringModifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        System.out.println("Teoria D3 " + "\n" +"Arrow/Lambda Function");

        User aldo = new User("Aldo", "Baglio", 200);
        User giovanni = new User("Giovanni", "Storti", 30);
        User giacomo = new User("Giacomo", "Poretti", 40);


        // Interfacce Funzionali Custom
        StringModifier wrapper = str -> "-----" + str + "-----";
        StringModifier inverter = str -> {
            String[] c = str.split("");
            String inverted  = "";
            for (int i = c.length - 1; i >= 0; i--) {
                inverted += c[i];
            }
            return inverted;
        };

        System.out.println(wrapper.modify("ciao"));
        System.out.println(wrapper.modify("alfonso"));
        System.out.println(inverter.modify("ciao"));
        System.out.println(inverter.modify("anna"));


        // Predicates
        Predicate<Integer> isMoreThanZero = num -> num > 0;
        Predicate<Integer> isLessThanHundred = num -> num < 100;
        Predicate<Integer> isBetweenZeroAndHundred = isMoreThanZero.and(isLessThanHundred);
        Predicate<User> hasNameMoreThanThreeLetters = user -> user.getName().length() > 3;

        System.out.println(isMoreThanZero.test(aldo.getAge()));
        System.out.println(isLessThanHundred.test(aldo.getAge()));
        System.out.println(isBetweenZeroAndHundred.test(aldo.getAge()));
        System.out.println(isBetweenZeroAndHundred.negate().test(aldo.getAge()));
        System.out.println(hasNameMoreThanThreeLetters.test(aldo));


        // Supplier => Interfaccia Funzionale che ritorna un tipo specifico
        Supplier<Integer> intSupplier = () -> {
            Random rdm = new Random();
            return rdm.nextInt(1, 100);
        };

        // ESEMPIO di lambda collegati alle collection

        List<Integer> interi = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            interi.add(intSupplier.get());
        }

        // Sono la stessa cosa
//        interi.forEach(i -> System.out.println(i));
        interi.forEach(System.out::println);

//        Set<Integer> interiDistinti = new HashSet<>();
//
//        for (int i = 0; i < 50; i++) {
//            interiDistinti.add(intSupplier.get());
//        }
//
//        interiDistinti.forEach(System.out::println);


        // STREAMs
        System.out.println("Teoria D3" + "\n" + "Streams");

        // Deve sempre essere terminato
        Predicate<Integer> isMoreThanTen = num -> num > 10;
        Predicate<Integer> isLessThanTwenty = num -> num < 20;

        // Stream terminato
        // FOREACH
        interi.stream().filter(isMoreThanTen.and(isLessThanTwenty).negate()).forEach(System.out::println);

        // Stream terminato
        // REDUCE ad un risultato
        int result = interi.stream().reduce(0, (subtotale, element) -> subtotale + element);
        System.out.println(result);

        //TOLIST ritorna una lista


        //MATCH
        if (interi.stream().allMatch(n -> n >= 18)) {
            System.out.println("tutti superiori di 18");
        }else{
            System.out.println("c'è almeno un numero minore di 18");
        }

        if (interi.stream().anyMatch(n -> n >= 18)) {
            System.out.println("c'è almeno un numero superiore di 18");
        }


        // DATE
        LocalDate today = LocalDate.now();
        System.out.println(today);

        LocalDate tomorrow = today.plusDays(1);
        System.out.println(tomorrow);

        LocalDate yesterday = today.minusDays(1);
        System.out.println(yesterday);

        System.out.println(yesterday.isBefore(tomorrow));
        int thisYear = today.getYear();
        System.out.println(thisYear);


    }
}