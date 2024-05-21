package com.company.project.model.service;

import com.company.project.model.Promotion;
import com.company.project.model.repository.PromotionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionService {
    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotionById(Long id) {
        return (Promotion) promotionRepository.findById(id).orElse(null);
    }

    public Promotion addPromotion(Promotion promotion) {
        // Verifică dacă promoția există deja
        Promotion existingPromotion = promotionRepository.findByName(promotion.getName());
        if (existingPromotion != null) {
            throw new IllegalArgumentException("Promotion with name " + promotion.getName() + " already exists");
        }
        // Adaugă promoția în baza de date
        return promotionRepository.save(promotion);
    }

    public Promotion updatePromotion(Long id, Promotion updatedPromotion) {
        Promotion existingPromotion = (Promotion) promotionRepository.findById(id).orElse(null);
        if (existingPromotion != null) {
            // Actualizează detaliile promoției
            existingPromotion.setName(updatedPromotion.getName());
            existingPromotion.setProductIds(updatedPromotion.getProductIds());
            // Salvează promoția actualizată în baza de date
            return promotionRepository.save(existingPromotion);
        }
        return null; // Returnează null dacă promoția nu există
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }
}
