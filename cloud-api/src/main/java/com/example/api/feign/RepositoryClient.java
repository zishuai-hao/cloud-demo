package com.example.api.feign;

import com.example.common.constant.ServiceConstant;
import com.example.common.util.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = ServiceConstant.SERVICE_REPO_NAME)
public interface RepositoryClient {

    String API_PREFIX = "/repo";

    @PutMapping(API_PREFIX + "/inventory/{productId}/_release")
    Result releaseInventory(@PathVariable Long productId);
}
