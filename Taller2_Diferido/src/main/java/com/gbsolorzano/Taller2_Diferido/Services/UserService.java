package com.gbsolorzano.Taller2_Diferido.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.gbsolorzano.Taller2_Diferido.Models.Entities.User;
import com.gbsolorzano.Taller2_Diferido.Models.Entities.Password;


public interface UserService {
    
    List<User> findAll();
    User findOneByIdentifier(String identifier);
    User findPassword(String password);
    User findOneById(String ID);
    boolean guardarunUser(User persona);
    boolean removeUser(User persona);
    void updateUser(User user);
    void updateUserState(User user);

}
