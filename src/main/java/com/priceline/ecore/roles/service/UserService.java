package com.priceline.ecore.roles.service;

import com.priceline.ecore.roles.service.client.dto.UserDTO;

public interface UserService {

    UserDTO find(String id);
}
