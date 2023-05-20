package com.priceline.ecore.roles.service.client;

import com.priceline.ecore.roles.service.client.dto.TeamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "teams", url = "${feign.url}")
public interface TeamClientService {

    @GetMapping("/teams")
    List<TeamDTO> findAll();

    @GetMapping("/teams/{id}")
    Optional<TeamDTO> find(@PathVariable String id);
}
