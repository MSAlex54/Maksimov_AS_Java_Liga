package com.liga.backend.domain.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private LocalDate birthDay;

    @Column
    private char sex;

    @Column
    private String interests;

    @Column
    private String city;

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "friendId")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private @Singular
    Set<User> friends;

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "friendId"),
            inverseJoinColumns = @JoinColumn(name = "userId")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private @Singular
    Set<User> friendOfs;

}
