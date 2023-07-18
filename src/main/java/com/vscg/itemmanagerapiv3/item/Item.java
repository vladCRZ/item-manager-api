package com.vscg.itemmanagerapiv3.item;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name="full_description", columnDefinition = "TEXT")
    private String fullDescription;

    private String price;
}