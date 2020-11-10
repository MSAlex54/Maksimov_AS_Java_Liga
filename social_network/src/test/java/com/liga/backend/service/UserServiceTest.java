package com.liga.backend.service;

import com.liga.backend.domain.dto.UserEditDto;
import com.liga.backend.domain.dto.UserRegistrationDto;
import com.liga.backend.domain.entity.User;
import com.liga.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    UserRepository userRepository;
    UserService userService;

    User entity;
    UserEditDto editDto;
    UserRegistrationDto registrationDto;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);

        entity = User.builder()
                .firstName("firstName")
                .lastName("lastName")
                .sex('f')
                .city("Raccon")
                .build();
        editDto = UserEditDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .sex('f')
                .city("Raccon")
                .build();
        registrationDto = UserRegistrationDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .build();
    }

    @Test
    void createUser() {
        when(userRepository.save(any())).thenReturn(entity);
        UserEditDto createdUser = userService.createUser(registrationDto);
        assertThat(createdUser).isNotNull();
        assertEquals(createdUser, editDto);
    }

    @Test
    void updateUser() {
        when(userRepository.save(any())).thenReturn(entity);
        UserEditDto updatedUser = userService.updateUser(editDto);
        assertThat(updatedUser).isNotNull();
        assertEquals(updatedUser, editDto);
    }

    @Test
    void getUser() {
        when(userRepository.getUserById(any())).thenReturn(entity);
        UserEditDto findedUser = userService.getUser(UUID.randomUUID());
        assertThat(findedUser).isNotNull();
        assertEquals(findedUser, editDto);
    }

    @Test
    void deleteUser() {

    }

    @Test
    void addFriends() {
//        when(userRepository.getUserById(any())).thenReturn(entity);
//        when(userRepository.findUserByIdList(any())).thenReturn(new HashSet<User>(Arrays.asList(entity)));
//        when(userRepository.save(any())).thenReturn(entity);
//        userService.addFriends(new Friendship(UUID.randomUUID(), new UUID[]{UUID.randomUUID()}));
//        assertThat(entity.getFriends().containsAll(entity.getFriendOfs()));
//        assertThat(entity.getFriends()).isNotNull();
//        assertThat(entity.getFriendOfs()).isNotNull();
    }

    @Test
    void deleteFriends() {

    }

    @Test
    void findUsersByParam() {

    }
}