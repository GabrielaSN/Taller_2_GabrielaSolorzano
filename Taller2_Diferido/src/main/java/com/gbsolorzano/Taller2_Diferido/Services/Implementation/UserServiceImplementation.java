package com.gbsolorzano.Taller2_Diferido.Services.Implementation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Service;

import com.gbsolorzano.Taller2_Diferido.Models.Entities.Password;
import com.gbsolorzano.Taller2_Diferido.Models.Entities.User;
import com.gbsolorzano.Taller2_Diferido.Services.UserService;

@Service
public class UserServiceImplementation implements UserService {
    private static List<User> users = new ArrayList<>();
	
	static {
		users.add(new User("1", "GabrielaS", "00185119@uca.edu.sv", "Gabriela", "0102030405#@xyz","active","16/05/2020","Admin"));

    }
	
	
    @Override
    public List < User > findAll() {
        return users;
    }  
    
    @Override
	public User findOneById(String ID) {
		return users.stream()
				.filter(u -> (u.getID().equals(ID)))
				.findAny()
				.orElse(null);
	}
	@Override
	public User findOneByIdentifier(String identifier) {
		return users.stream()
				.filter(u -> (u.getUsername().equals(identifier) || u.getEmail().equals(identifier) ))
				.findAny()
				.orElse(null);
	}
    @Override
    public User findPassword(String password) {
		return users.stream()
				.filter(u -> (u.getPassword().equals(password)))
				.findAny()
				.orElse(null);
    }
    @Override
    public boolean guardarunUser(User persona) {
        return  users.add(persona);
    }
    @Override
    public boolean removeUser(User persona) {
        return  users.remove(persona);
    }
    @Override
	public void updateUser(User updatedUser) {
        User existingUser = findOneById(updatedUser.getID() );
        if (existingUser == null) {
            throw new IllegalArgumentException("No se pudo encontrar el usuario con el ID: " + updatedUser.getID());
        }
        existingUser.setPassword(updatedUser.getPassword());
    }

    @Override
	public void updateUserState(User updatedUserState) {
        User existingUser = findOneById(updatedUserState.getID());
        
        if (existingUser == null) {
            throw new IllegalArgumentException("No se pudo encontrar el usuario con el ID: " + updatedUserState.getID());
        }
        existingUser.setStatus(updatedUserState.getStatus());
    }
}
