package com.anaboru.springbootstrap.services;

import com.anaboru.springbootstrap.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> getRoles();


    Role findByRole(String name);

    void save(Role role);
}
