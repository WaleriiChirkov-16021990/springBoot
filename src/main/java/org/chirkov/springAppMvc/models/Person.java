package org.chirkov.springAppMvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Objects;


@Getter
@Entity
@Table(name = "Person")
@Setter
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty!")
    @Size(min = 3, max = 33, message = "First name should 3-33 characters long!")
    @Column(name = "name")
    private String name;
    @NotEmpty(message = "Surname should not be empty!")
    @Size(min = 3, max = 33, message = "Surname should 3-33 characters long!")
    @Column(name = "surname")
    private String surname;
    @Range(min = 1, max = 333, message = "Age should be between 1 - 333 and ")
    @Column(name = "age")
    private int age;
    @NotEmpty(message = "Email should not be empty!")
    @Email(message = "Email should be valid")
    @Column(name = "email")
    private String email;
    // Страна, Город, индекс(6 цифр)
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, \\d{6}", message = "Your address should be in this format : 'Country, City, Postal code (6 digits)'")
    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy") // дд/мм/гггг
    private Date dateOfBirth;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "mood")
    private Mood mood;

    @OneToMany(mappedBy = "owner")
    private List<Item> itemList;


    public Person(String name, String surname, int age, String email, String address) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public Person() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return getId() == person.getId() && getAge() == person.getAge() && Objects.equals(getName()
                , person.getName()) && Objects.equals(getSurname()
                , person.getSurname()) && Objects.equals(getEmail()
                , person.getEmail()) && Objects.equals(getAddress()
                , person.getAddress()) && Objects.equals(getDateOfBirth()
                , person.getDateOfBirth()) && Objects.equals(getCreatedAt()
                , person.getCreatedAt()) && getMood() == person.getMood();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getAge(), getEmail()
                , getAddress(), getDateOfBirth(), getCreatedAt(), getMood());
    }

}
