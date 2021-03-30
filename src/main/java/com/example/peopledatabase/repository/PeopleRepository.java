package com.example.peopledatabase.repository;

import com.example.peopledatabase.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository  extends JpaRepository<Person,Long> {

    List<Person> findByDisable(boolean disable);
    List<Person> findByRole(String role);

}
