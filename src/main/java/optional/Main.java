package optional;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Optional<String> empty = Optional.ofNullable("Hello");

//        System.out.println(empty);
//        System.out.println(empty.get());
        System.out.println(empty.isPresent());
        System.out.println(empty.isEmpty());

        String orElse = empty
                .map(String::toLowerCase)
//                .orElse("world");
//                .or()     //to retrieve an Optional not a String
//                .orElseThrow(IllegalStateException::new);
        .orElseGet(()->{
            //....extra computation to retrieve the value example get data from db
            return "world";
        });
        System.out.println(orElse);


        empty.ifPresent(word -> System.out.println("hello "+word));
        empty.ifPresent(System.out::println);

        empty.ifPresentOrElse(word -> {
            System.out.println(word);
        },()->{
            System.out.println("Not found");
        });


//        Person person = new Person("abdo", "ABdo@gmail.com");
//        System.out.println(person.getEmail().toLowerCase());


        Person person2 = new Person("abdo", null);

        System.out.println(person2.getEmail()
                .map(String::toLowerCase)
                .orElse("email not provided"));
        //like the above
        if(person2.getEmail().isPresent()){
            String email = person2.getEmail().get();
            System.out.println(email.toLowerCase());
        }else {
            System.out.println("email not provided");
        }


    }


}

class Person{
    private final String name;
    private final String email;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }
}
