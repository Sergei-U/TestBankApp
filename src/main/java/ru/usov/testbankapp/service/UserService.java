package ru.usov.testbankapp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.testbankapp.entity.User;
import ru.usov.testbankapp.repository.UserRepository;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public BigDecimal getBalance(Long id) {
        return userRepository.getById(id).getBalance();
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }


}
