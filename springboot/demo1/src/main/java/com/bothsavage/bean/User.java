package com.bothsavage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class User {
    String name;
    Pet pe;
}
