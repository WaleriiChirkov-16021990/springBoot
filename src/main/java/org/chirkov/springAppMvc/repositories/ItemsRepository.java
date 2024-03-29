package org.chirkov.springAppMvc.repositories;

import org.chirkov.springAppMvc.models.Item;
import org.chirkov.springAppMvc.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Item, Integer> {
    List<Item> findItemsByOwner(Person person);

    List<Item> findItemsByItemName(String itemName);
}
