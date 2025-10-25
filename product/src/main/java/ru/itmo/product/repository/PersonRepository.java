package ru.itmo.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.product.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
}
