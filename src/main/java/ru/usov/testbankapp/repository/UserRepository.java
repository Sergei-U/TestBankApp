package ru.usov.testbankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.usov.testbankapp.entity.Operations;
import ru.usov.testbankapp.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Override
    Optional<User> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    List<Operations> findOperationBetween(LocalDate startDate, LocalDate endDate);

    List<Operations> findOperationsByDateOperationAfter(LocalDate date);

    List<Operations> findOperationsByDateOperationBefore(LocalDate date);
}
