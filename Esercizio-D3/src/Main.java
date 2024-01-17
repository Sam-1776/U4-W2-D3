import it.sam.be.categorie.Categorie;
import it.sam.be.classi.Customer;
import it.sam.be.classi.Order;
import it.sam.be.classi.Product;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        System.out.println("Esercizio D3");

        Random rdm = new Random();
        Scanner input = new Scanner(System.in);

        Integer nC = rdm.nextInt(50);
        Long id = rdm.nextLong();
        LocalDate inizio = LocalDate.parse("2021-02-01");
        LocalDate fine = LocalDate.parse("2021-04-01");
        Predicate<Order> isBetween = x -> (x.orderDate.isAfter(inizio)) && (x.orderDate.isBefore(fine));
        Predicate<Product> isMinorThanHundred = x -> x.price < 100;
        Predicate<Product> isCategoryBoy = x -> x.category.equals(Categorie.boys);

        List<Product> warehouse = new ArrayList<>();
        List<Customer> utenti = new ArrayList<>();
        List<Order> ordini = new ArrayList<>();

        System.out.println("Inserire nome");
        String nome = input.nextLine();
        Customer utente = new Customer(id,nome, 2);

        generateCustomer(utenti);
        utenti.stream().forEach(System.out::println);


        if (nC == 0){
            System.out.println("Non ci sono prodotti in magazzino");
        }else {
            System.out.println("Prodotti in magazzino: " + nC);
            for (int j = 0; j < nC; j++) {
                Integer t = rdm.nextInt(1,4);
                Double p = rdm.nextDouble(200.0);
                Long i = rdm.nextLong(100000);
                String str = String.valueOf(generateName());
                if (t == 1){
                    warehouse.add(new Product(i,str, Categorie.books, p));
                } else if (t == 2) {
                    warehouse.add(new Product(i,str,Categorie.baby,p));
                }else {
                    warehouse.add(new Product(i,str,Categorie.boys,p));
                }
            }
            Integer nCO = rdm.nextInt(50);
            for (int i = 0; i < nCO; i++) {
                Integer t = rdm.nextInt(1,3);

                List<Product> order = new ArrayList<>();
                order.addAll(generateOrder(warehouse));
                LocalDate orderDay = generateData();
                if (t == 1) {
                    ordini.add(new Order(id,"completed", orderDay, orderDay.plusDays(5), order, takeOneCustomer(utenti)));
                }else {
                    ordini.add(new Order(id,"Pending", orderDay, orderDay.plusDays(5), order, takeOneCustomer(utenti)));
                }

            }
        }



        System.out.println("----- Esercizio 1 -----");
        warehouse.stream().filter(isMinorThanHundred).forEach(product -> System.out.println(product));

        System.out.println("----- Esercizio 2 -----");

        System.out.println("----- Esercizio 3 -----");
        warehouse.stream().filter(isCategoryBoy).map(p -> {
            p.price -= p.price * 0.1;
            return p;
        }).forEach(System.out::println);

        System.out.println("----- Esercizio 4 -----");
        ordini.stream().filter(order -> order.customer.tier.equals(2)).filter(isBetween).forEach(System.out::println);

    }


    public static StringBuilder generateName(){
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = lower.toUpperCase();
        String perRandom = upper + lower;
        int lunghezzaRandom = 5;

        SecureRandom sr = new SecureRandom();
        StringBuilder sb = new StringBuilder(lunghezzaRandom);
        for (int i = 0; i < lunghezzaRandom; i++) {
            int randomInt = sr.nextInt(perRandom.length());
            char randomChar = perRandom.charAt(randomInt);
            sb.append(randomChar);
        }

        return sb;
    }

    public static List<Customer> generateCustomer(List<Customer> x){
        Random rdm = new Random();
        for (int i = 0; i < 10; i++) {
            Long id = rdm.nextLong(1000000);
            String str = String.valueOf(generateName());
            Integer t = rdm.nextInt(1,4);
            x.add(new Customer(id,str,t));
        }
        return x;

    }

    public static List<Product> generateOrder(List<Product> x){
        Random rdm = new Random();
        List<Product> y = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Integer nP = rdm.nextInt( x.size() -1);
            y.add(x.get(nP));
        }
        return y;
    }

    public static Customer takeOneCustomer(List<Customer> x){
        Random rdm = new Random();
        Integer nC = rdm.nextInt( x.size() - 1 );
        Customer newUser = x.get(nC);
        return newUser;
    }

    public static LocalDate generateData (){
            Random rdm = new Random();

            int month = rdm.nextInt(12) + 1;
            int maxDay = LocalDate.of(2021, month, 1).lengthOfMonth();
            int day = rdm.nextInt(maxDay) + 1;

            LocalDate randomDate = LocalDate.of(2021, month, day);

            return randomDate;

    }
}