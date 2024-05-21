package com.company.project.controller;

import com.company.project.model.Promotion;
import com.company.project.model.service.PromotionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotions = promotionService.getAllPromotions();
        return new ResponseEntity<>(promotions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promotion> getPromotionById(@PathVariable Long id) {
        Promotion promotion = promotionService.getPromotionById(id);
        if (promotion != null) {
            return new ResponseEntity<>(promotion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Promotion> addPromotion(@RequestBody Promotion promotion) {
        Promotion savedPromotion = promotionService.addPromotion(promotion);
        return new ResponseEntity<>(savedPromotion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotion> updatePromotion(@PathVariable Long id, @RequestBody Promotion updatedPromotion) {
        Promotion promotion = promotionService.updatePromotion(id, updatedPromotion);
        if (promotion != null) {
            return new ResponseEntity<>(promotion, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long id) {
        promotionService.deletePromotion(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
