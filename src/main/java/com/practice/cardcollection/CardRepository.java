package com.practice.cardcollection;

import org.springframework.data.jpa.repository.JpaRepository;

interface CardRepository extends JpaRepository<Card, Long> {

}
