package org.chirkov.springAppMvc.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
//import org.hibernate.annotations.Table;

@Getter
@Entity
@Table(name = "Item")
@Setter
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "tem name schould not be empty")
    @Column(name = "item_name")
    private String itemName;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Item() {
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Item(String itemName, Person owner) {
        this.itemName = itemName;
        this.owner = owner;
    }

}
