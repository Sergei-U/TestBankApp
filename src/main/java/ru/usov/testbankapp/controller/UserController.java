package ru.usov.testbankapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.usov.testbankapp.entity.User;
import ru.usov.testbankapp.service.UserService;

@RestController("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = {"application/json"}, produces = {"application/json"})
    public void createUser(@RequestParam User user) {
        userService.addNewUser(user);
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    @RequestMapping(method = RequestMethod.POST, value = "/update", consumes = {"application/json"}, produces = {"application/json"})
    public void updateUser(@RequestParam User user) {
        userService.updateUser(user);
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
