package lambda;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Run {
    public static void main(String[] args) {

        ArrayList<Person> personList  = new ArrayList<Person>() ;

        Person personA = new Person("A",1);
        Person personB = new Person("B",6);
        Person personC = new Person("C",10);

        personList.add(personA);
        personList.add(personB);
        personList.add(personC);

        System.out.println("---------------自定义Lambda表达式-----------");
        checkAndExecute(p -> p.getAge()>3,p -> System.out.println(p),personList);
        System.out.println("---------------java.util.function工具类-----");
        checkAndExecute2(p -> p.getAge()<3,p -> System.out.println(p),personList);
        System.out.println("---------------foreach来了------------------");
        personList.forEach(p -> {if(p.getAge()>6) System.out.println(p);});
        System.out.println("---------------foreach配合上filter-----------");
        personList.stream().filter(p -> p.getAge()>3).forEach(p -> System.out.println(p));
        System.out.println("---------------method reference出场----------");
        personList.stream().filter(p -> p.getAge()<7).forEach(System.out::println);


    }

    static void  checkAndExecute(Agechecker agechecker,Executor executor,List<Person> personList){
        for (Person person :personList){
            if(agechecker.checkAge(person)){
                executor.execute(person);
            }
        }

    }

    static void checkAndExecute2(Predicate<Person> predicate, Consumer<Person> consumer,List<Person> personList){
        for (Person person :personList){
            if(predicate.test(person)){
                consumer.accept(person);
            }
        }
    }

}
