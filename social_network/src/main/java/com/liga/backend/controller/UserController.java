package com.liga.backend.controller;

import com.liga.backend.domain.dto.Friendship;
import com.liga.backend.domain.dto.UserEditDto;
import com.liga.backend.domain.dto.UserListDto;
import com.liga.backend.domain.dto.UserRegistrationDto;
import com.liga.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "users", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserEditDto createUser(@RequestBody UserRegistrationDto user) {
        UserEditDto createdUser = this.userService.createUser(user);
        return createdUser;
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public UserEditDto updateUser(@PathVariable("id") UUID id, @RequestBody UserEditDto user) {
        return this.userService.updateUser(user);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") UUID id) {
        this.userService.deleteUser(id);
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserEditDto getUser(@PathVariable("id") UUID id) {
        UserEditDto user = this.userService.getUser(id);
        return user;
    }

    @RequestMapping(value = "/friends", method = RequestMethod.PUT)
    public void addFriends(@RequestBody Friendship friendship) {
        userService.addFriends(friendship);
    }

    @RequestMapping(value = "/friends", method = RequestMethod.DELETE)
    public void deleteFriends(@RequestBody Friendship friendship) {
        userService.deleteFriends(friendship);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    @ResponseBody
    public Set<UserListDto> search(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "age", required = false) Integer age
    ) {
        return userService.findUsersByParam(firstName, lastName, age);
    }
}

