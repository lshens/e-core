package com.priceline.roles.service.client;

import com.priceline.roles.service.client.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "users", url = "${feign.client.config.url}")
public interface UserClientService {

    @RequestMapping(method = RequestMethod.GET, value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDTO> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Optional<UserDTO> find(String id);
}
