package tfr.LostAndFoundAPP.DTO.entities;


import tfr.LostAndFoundAPP.entities.ItemLost;

import java.time.LocalDate;

public class ItemLostMinDTO {

        private String description;
        private LocalDate foundDate;

        public ItemLostMinDTO(ItemLost entity) {
            description = entity.getDescription();
            foundDate = entity.getFoundDate();
        }

        // Getters
        public String getDescription() {
            return description;
        }

        public LocalDate getFoundDate() {
            return foundDate;
        }
    }

