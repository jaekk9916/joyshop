package com.example.joyshop.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setTitle(String title) {
        if(title.length() > 255){
            System.out.println("Title can not be longer than 255 characters");
            return;
        }
        this.title = title;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(int price) {
        if(price < 0){
            System.out.println("Price can not be negative");
            return;
        }
        this.price = price;
    }

    private String title;
    private int price;
}
