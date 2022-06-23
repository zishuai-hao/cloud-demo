package com.example.repo.mapper;

import com.example.repo.entity.RepositoryEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface RepositoryMapper {
    @Select(value = "select * FROM t_repository WHERE product_id = #{productId} FOR UPDATE ")
    RepositoryEntity selectByproductId(@Param("productId") Long productId);

    @Update(value = "update t_repository set inventory = #{repository.inventory} WHERE inventory = #{repository.oldInventory}")
    int update(@Param("repository") RepositoryEntity repository);
}
