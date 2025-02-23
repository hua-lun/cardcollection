package com.practice.cardcollection;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class CardController {

    private final CardRepository cardRepository;

    private final CardModelAssembler cardModelAssembler;

    CardController(CardRepository cardRepository, CardModelAssembler cardModelAssembler) {
        this.cardRepository = cardRepository;
        this.cardModelAssembler = cardModelAssembler;
    }

    @GetMapping("/cards")
    CollectionModel<EntityModel<Card>> all() {
        List<EntityModel<Card>> cards = cardRepository.findAll().stream()
                                            .map(cardModelAssembler::toModel)
                                            .collect(Collectors.toList());

        return CollectionModel.of(cards, linkTo(methodOn(CardController.class).all()).withSelfRel());
    }

    @PostMapping("/cards")
    ResponseEntity<?> newCard(@RequestBody Card newCard) {
        EntityModel<Card> entityModel = cardModelAssembler.toModel(cardRepository.save(newCard));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @GetMapping("/cards/{id}")
    EntityModel<Card> one(@PathVariable Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new CardNotFoundException(id));

        return cardModelAssembler.toModel(card);
    }

    @PutMapping("/cards/{id}")
    ResponseEntity<?> updateCardQuantity(@PathVariable Long id, @RequestParam(name = "quantity", required = true, defaultValue = "1") Integer quantity) {

        Card updatedCard = cardRepository.findById(id)
            .map(card -> {
                card.setQuantity(card.getQuantity() + quantity);
                return cardRepository.save(card);
            }).orElse(null);

        assert updatedCard != null;
        EntityModel<Card> entityModel = cardModelAssembler.toModel(updatedCard);
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/cards/{id}")
    ResponseEntity<?> deleteCard(@PathVariable Long id) {
        cardRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
