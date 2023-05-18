package com.priceline.roles.service.client;

import com.priceline.roles.service.client.dto.TeamDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "teams", url = "${feign.client.config.url}")
public interface TeamClientService {

    @RequestMapping(method = RequestMethod.GET, value = "/teams", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<TeamDTO> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/teams/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    TeamDTO find(String id);
}
