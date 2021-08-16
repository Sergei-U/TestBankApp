package ru.usov.testbankapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.testbankapp.entity.NameOperation;
import ru.usov.testbankapp.entity.Operations;
import ru.usov.testbankapp.repository.OperationsRepository;
import ru.usov.testbankapp.repository.UserRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BalanceService {


    private final UserRepository userRepository;
    private final OperationsRepository operationsRepository;

    @Transactional
    public BigDecimal takeMoney(Long id, BigDecimal sum) {
        userRepository.getById(id).setBalance(userRepository.getById(id).getBalance().subtract(sum));
        List<Operations> operationsList = new ArrayList<>();
        operationsList.add(new Operations(LocalDate.now(), NameOperation.TAKE_MONEY));
        userRepository.getById(id).setOperationsList(operationsList);
        return userRepository.getById(id).getBalance();
    }

    @Transactional
    public BigDecimal putMoney(Long id, BigDecimal sum) {
        userRepository.getById(id).setBalance(userRepository.getById(id).getBalance().add(sum));
        List<Operations> operationsList = new ArrayList<>();
        operationsList.add(new Operations(LocalDate.now(), NameOperation.PUT_MONEY));
        userRepository.getById(id).setOperationsList(operationsList);

        return userRepository.getById(id).getBalance();
    }

    public List<Operations> getOperationList(Long id, LocalDate startDate, LocalDate endDate) {

        if (startDate == null)
            operationsRepository.findOperationsByDateOperationBefore(endDate);
        else if (endDate == null)
            operationsRepository.findOperationsByDateOperationAfter(startDate);
        else operationsRepository.findOperationsByDateOperationBetween(startDate, endDate);

        return userRepository.getById(id).getOperationsList();
    }

    @Transactional
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
