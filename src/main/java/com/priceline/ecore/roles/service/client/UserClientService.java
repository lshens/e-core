package com.priceline.ecore.roles.service.client;

import com.priceline.ecore.roles.service.client.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "users", url = "${feign.url}")
public interface UserClientService {

    @GetMapping("/users")
    List<UserDTO> findAll();

    @GetMapping("/users/{id}")
    Optional<UserDTO> find(@PathVariable String id);
}
