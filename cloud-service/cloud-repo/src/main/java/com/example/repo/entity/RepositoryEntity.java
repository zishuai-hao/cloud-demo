package com.example.repo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryEntity {
    private Long id;
    private Long productId;
    private Long inventory;
    private Long oldInventory;
}
