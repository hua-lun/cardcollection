package com.practice.cardcollection;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {

    private final CardRepository cardRepository;

    CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping("/cards")
    List<Card> getCards() {
        return cardRepository.findAll();
    }

    @PostMapping("/cards")
    Card addCard(@RequestBody Card card) {
        return cardRepository.save(card);
    }

    @GetMapping("/cards/{id}")
    Card getCard(@PathVariable Long id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));
    }

    @PutMapping("/cards/{id}")
    Card updateCardQuantity(@PathVariable Long id, @RequestParam(name = "quantity", required = true, defaultValue = "1") Integer quantity) {

        return cardRepository.findById(id)
            .map(card -> {
                card.setQuantity(card.getQuantity() + quantity);
                return cardRepository.save(card);
            }).orElse(null);
    }

    @DeleteMapping("/cards/{id}")
    void deleteCard(@PathVariable Long id) {
        cardRepository.deleteById(id);
    }

}
