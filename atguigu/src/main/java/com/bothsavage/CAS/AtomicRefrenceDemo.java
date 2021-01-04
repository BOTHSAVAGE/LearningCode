package com.bothsavage.CAS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@AllArgsConstructor
@ToString
class User{
    String userName;
    int age;
}

public class AtomicRefrenceDemo {
    public static void main(String[] args) {
        User user1 = new User("user1",11);
        User user2 = new User("user2",22);

        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);
        System.out.println(atomicReference.compareAndSet(user1, user2)+"\t "+atomicReference.get().toString());
    }
}
