package ru.usov.testbankapp.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.usov.testbankapp.entity.Operations;
import ru.usov.testbankapp.service.BalanceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController("/balance")
@AllArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    @RequestMapping(method = RequestMethod.POST, value = "/take")
    public BigDecimal takeMoney(@RequestParam Long id,
                                @RequestParam BigDecimal sum) {
        return balanceService.takeMoney(id, sum);
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    @RequestMapping(method = RequestMethod.POST, value = "/put")
    public BigDecimal putMoney(@RequestParam Long id,
                               @RequestParam BigDecimal sum) {
        return balanceService.putMoney(id, sum);
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<Operations> getOperations(@RequestParam Long id,
                                          @RequestParam LocalDate startDate,
                                          @RequestParam LocalDate endDate) {
        return balanceService.getOperationList(id, startDate, endDate);
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    @RequestMapping(method = RequestMethod.POST, value = "/get")
    public void transferMoney(@RequestParam Long usrFrom,
                              @RequestParam Long usrTo,
                              @RequestParam BigDecimal sum) {
        balanceService.transferMoney(usrFrom, usrTo, sum);
    }
}
