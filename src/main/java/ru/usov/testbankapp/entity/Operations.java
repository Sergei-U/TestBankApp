package ru.usov.testbankapp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Operations")
@Getter
@Setter
public class Operations {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateOperation;
    private NameOperation nameOperation;

    public Operations(LocalDate dateOperation, NameOperation nameOperation) {
        this.dateOperation = dateOperation;
        this.nameOperation = nameOperation;
    }

    public Operations() {

    }
}
