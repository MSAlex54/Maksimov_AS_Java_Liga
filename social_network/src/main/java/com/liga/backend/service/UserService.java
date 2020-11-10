package com.liga.backend.service;

import com.liga.backend.domain.dto.Friendship;
import com.liga.backend.domain.dto.UserEditDto;
import com.liga.backend.domain.dto.UserListDto;
import com.liga.backend.domain.dto.UserRegistrationDto;
import com.liga.backend.domain.entity.User;
import com.liga.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public UserEditDto createUser(UserRegistrationDto dto) {
        User entity = toEntityFromUserRegistrationDto(dto);
        return toEditDto(repository.save(entity));
    }

    public UserEditDto updateUser(UserEditDto dto) {
        User entity = toEntity(dto);
        return toEditDto(repository.save(entity));
    }

    public UserEditDto getUser(UUID id) {
        return toEditDto(repository.getUserById(id));
    }


    public void deleteUser(UUID id) {
        repository.deleteById(id);
    }

    public void addFriends(Friendship friendship) {
        User user = repository.getUserById(friendship.getUserUUID());
        Set<User> newFriends = repository.findUserByIdList(Arrays.asList(friendship.getFriendsUUIDs()));
        user.getFriends().addAll(newFriends);
        repository.save(user);
        newFriends.stream().forEach(u -> {
            u.getFriendOfs().add(user);
            repository.save(u);
        });
    }

    public void deleteFriends(Friendship friendship) {
        User user = repository.getUserById(friendship.getUserUUID());
        Set<User> notFriends = repository.findUserByIdList(Arrays.asList(friendship.getFriendsUUIDs()));
        user.getFriends().removeAll(notFriends);
        repository.save(user);
        notFriends.stream().forEach(u -> {
            u.getFriendOfs().remove(user);
            repository.save(u);
        });
    }

    private User toEntity(UserEditDto dto) {
        return User.builder()
                .id(dto.getId())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .birthDay(dto.getBirthDay())
                .sex(dto.getSex())
                .interests(dto.getInterests())
                .city(dto.getCity())
                .friends(repository.findUserByIdList(dto.getFriends()))
                .friendOfs(repository.findUserByIdList(dto.getFriendOfs()))
                .build();
    }

    private UserEditDto toEditDto(User entity) {
        return UserEditDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDay(entity.getBirthDay())
                .sex(entity.getSex())
                .interests(entity.getInterests())
                .city(entity.getCity())
                .friends(entity.getFriends().stream().map(User::getId).collect(Collectors.toSet()))
                .friendOfs(entity.getFriendOfs().stream().map(User::getId).collect(Collectors.toSet()))
                .build();
    }


    private User toEntityFromUserRegistrationDto(UserRegistrationDto dto) {
        return User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .sex(dto.getSex())
                .build();
    }

    private UserListDto toListDto(User entity) {
        return UserListDto.builder()
                .id(entity.getId())
                .fullName(entity.getFirstName() + " " + entity.getLastName())
                .age(Period.between(entity.getBirthDay(), LocalDate.now()).getYears())
                .sex(entity.getSex())
                .interests(entity.getInterests())
                .city(entity.getCity())
                .build();
    }

    public Set<UserListDto> findUsersByParam(String firstName, String lastName, Integer age) {
        String patternName = "^[а-яА-ЯёЁa-zA-Z]+$";
        if (firstName == null) {
            firstName = "";
        } else {
            firstName = firstName.matches(patternName) ? firstName : "";
        }
        if (lastName == null) {
            lastName = "";
        } else {
            lastName = lastName.matches(patternName) ? lastName : "";
        }
        age = age == null ? 0 : age;
        Set<User> data = new HashSet<>();

        if (firstName.equals("") & lastName.equals("") & age == 0) data = (Set<User>) repository.findAll();

        if (!firstName.equals("") & lastName.equals("") & age == 0) data = repository.findAllByFirstNameLike(firstName);


        if (firstName.equals("") & !lastName.equals("") & age == 0) data = repository.findAllByLastNameLike(lastName);

        if (!firstName.equals("") & !lastName.equals("") & age == 0)
            data = repository.findAllByFirstNameLikeAndLastNameLike(firstName, lastName);

        if (!firstName.equals("") & !lastName.equals("") & age != 0) data = repository.
                findAllByFirstNameLikeAndLastNameLikeAndBirthDayIsBetween(firstName, lastName,
                        LocalDate.now().minusYears(age + 1), LocalDate.now().minusYears(age));

        if (!firstName.equals("") & lastName.equals("") & age != 0) data = repository.
                findAllByFirstNameLikeAndBirthDayIsBetween(firstName,
                        LocalDate.now().minusYears(age + 1), LocalDate.now().minusYears(age));

        if (firstName.equals("") & !lastName.equals("") & age != 0) data = repository.
                findAllByLastNameLikeAndBirthDayIsBetween(lastName,
                        LocalDate.now().minusYears(age + 1), LocalDate.now().minusYears(age));

        if (firstName.equals("") & lastName.equals("") & age != 0) data = repository.
                findAllByBirthDayIsBetween(LocalDate.now().minusYears(age + 1), LocalDate.now().minusYears(age));

        Set<UserListDto> result = new HashSet<>();
        result = data.stream().map(this::toListDto).collect(Collectors.toSet());
        return result;
    }
}
