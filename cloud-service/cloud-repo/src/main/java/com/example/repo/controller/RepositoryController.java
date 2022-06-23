package com.example.repo.controller;

import com.example.common.util.Result;
import com.example.repo.service.RepositoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/repo")
@RequiredArgsConstructor
public class RepositoryController {

    private final RepositoryService repositoryService;

    @PutMapping("/inventory/{productId}/_release")
    public Result releaseInventory(@PathVariable Long productId) {
        return Result.of(repositoryService.releaseInventory(productId));
    }
}
