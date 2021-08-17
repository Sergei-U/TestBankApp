package ru.usov.testbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usov.testbankapp.entity.Operations;

@Repository
public interface OperationsRepository extends JpaRepository<Operations, Long> {


}
