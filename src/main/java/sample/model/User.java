package sample.model;

import lombok.Data;

import javax.persistence.*;
@Entity(name = "table1")
@Data
public class User {
    @Id
    Long id;

    @Column(name = "name")
    String name;
}
