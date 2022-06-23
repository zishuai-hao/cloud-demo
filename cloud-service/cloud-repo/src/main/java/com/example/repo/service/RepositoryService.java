package com.example.repo.service;

import com.example.common.exception.BaseException;
import com.example.repo.entity.RepositoryEntity;
import com.example.repo.mapper.RepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final RepositoryMapper repositoryMapper;

    public boolean releaseInventory(Long productId) {
        final RepositoryEntity repositoryEntity = repositoryMapper.selectByproductId(productId);
        if (repositoryEntity == null) {
            throw new BaseException("商品不存在");
        }
        if (repositoryEntity.getInventory() < 2) {
            throw new BaseException("库存太少了，不够用");
        }
        repositoryEntity.setOldInventory(repositoryEntity.getInventory());
        repositoryEntity.setInventory(repositoryEntity.getInventory() - 2);
        return repositoryMapper.update(repositoryEntity) > 0;
    }

}
