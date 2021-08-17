package ru.usov.testbankapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.testbankapp.entity.NameOperation;
import ru.usov.testbankapp.entity.Operations;
import ru.usov.testbankapp.repository.OperationsRepository;
import ru.usov.testbankapp.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BalanceService {


    private final UserRepository userRepository;
    private final OperationsRepository operationsRepository;


    public BigDecimal takeMoney(Long id, BigDecimal sum) {
        userRepository.getById(id).setBalance(userRepository.getById(id).getBalance().subtract(sum));
        List<Operations> operationsList = new ArrayList<>();
        operationsList.add(new Operations(LocalDate.now(), NameOperation.TAKE_MONEY));
        userRepository.getById(id).setOperationsList(operationsList);
        return userRepository.getById(id).getBalance();
    }

    public BigDecimal putMoney(Long id, BigDecimal sum) {
        userRepository.getById(id).setBalance(userRepository.getById(id).getBalance().add(sum));
        List<Operations> operationsList = new ArrayList<>();
        operationsList.add(new Operations(LocalDate.now(), NameOperation.PUT_MONEY));
        userRepository.getById(id).setOperationsList(operationsList);
        return userRepository.getById(id).getBalance();
    }

    public List<Operations> getOperationList(Long id, LocalDate startDate, LocalDate endDate) {
        if (startDate == null)
            return userRepository.findById(id).get()
                    .getOperationsList()
                    .stream().filter(operations -> operations.getDateOperation().isBefore(endDate)).collect(Collectors.toList());
        else if (endDate == null)
            return userRepository.findById(id).get()
                    .getOperationsList()
                    .stream().filter(operations -> operations.getDateOperation().isAfter(startDate)).collect(Collectors.toList());
        else
            return userRepository.findById(id).get()
                    .getOperationsList()
                    .stream().filter(operations -> operations.getDateOperation().isAfter(startDate) && operations.getDateOperation().isBefore(endDate)).collect(Collectors.toList());

    }


    public void transferMoney(Long usrFrom, Long usrTo, BigDecimal sum) {
        List<Operations> operationsFrom = new ArrayList<>();
        operationsFrom.add(new Operations(LocalDate.now(), NameOperation.TRANSFER_MONEY_SEND));
        List<Operations> operationsTo = new ArrayList<>();
        operationsTo.add(new Operations(LocalDate.now(), NameOperation.TRANSFER_MONEY_TAKE));
        userRepository.getById(usrFrom).setOperationsList(operationsFrom);
        userRepository.getById(usrTo).setOperationsList(operationsTo);
        userRepository.getById(usrFrom).setBalance(userRepository.getById(usrFrom).getBalance().subtract(sum));
        userRepository.getById(usrTo).setBalance(userRepository.getById(usrTo).getBalance().add(sum));
    }
}
