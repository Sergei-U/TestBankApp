package ru.usov.testbankapp.controller;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.usov.testbankapp.entity.Operations;
import ru.usov.testbankapp.service.BalanceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController("/balance")
@AllArgsConstructor
@Controller
public class BalanceController {

    private final BalanceService balanceService;

    @RequestMapping(method = RequestMethod.POST, value = "/take")
    public BigDecimal takeMoney(@RequestParam Long id,
                                @RequestParam BigDecimal sum) {
        return balanceService.takeMoney(id, sum);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/put")
    public BigDecimal putMoney(@RequestParam Long id,
                               @RequestParam BigDecimal sum) {
        return balanceService.putMoney(id, sum);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public List<Operations> getOperations(@RequestParam Long id,
                                          @RequestParam LocalDate startDate,
                                          @RequestParam LocalDate endDate) {
        return balanceService.getOperationList(id, startDate, endDate);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/get")
    public void transferMoney(@RequestParam Long usrFrom,
                              @RequestParam Long usrTo,
                              @RequestParam BigDecimal sum) {
        balanceService.transferMoney(usrFrom, usrTo, sum);
    }
}
