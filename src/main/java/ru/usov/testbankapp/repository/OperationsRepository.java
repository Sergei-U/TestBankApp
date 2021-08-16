package ru.usov.testbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usov.testbankapp.entity.Operations;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationsRepository extends JpaRepository<Operations, Long> {


    List<Operations> findOperationsByDateOperationAfter(LocalDate date);

    List<Operations> findOperationsByDateOperationBefore(LocalDate date);

    List<Operations> findOperationsByDateOperationBetween(LocalDate startDate, LocalDate endDate);

}
