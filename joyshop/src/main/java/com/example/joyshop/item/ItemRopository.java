package com.example.joyshop.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRopository extends JpaRepository<Item, Long> {

    Page<Item> findPageBy(Pageable page);
}