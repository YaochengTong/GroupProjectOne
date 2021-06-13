package com.app.groupprojectapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Entity
//don't forget to add the table and name
//@Table(name = "book")

//No need to write getter/setters or constructors if you are using the following four annotation.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExampleEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer bookId;
//
//    @Column(name = "name")
//    private String name;

    private Integer id;
}
