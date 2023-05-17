package com.gbsolorzano.Taller2_Diferido.Controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbsolorzano.Taller2_Diferido.Services.UserService;
import com.gbsolorzano.Taller2_Diferido.Models.Entities.User;
import com.gbsolorzano.Taller2_Diferido.Models.DTO.UpdatePasswordDTO;
import com.gbsolorzano.Taller2_Diferido.Models.DTO.SigninDTO;



import jakarta.validation.Valid;

@RestController
@RequestMapping("/main")
@CrossOrigin("*")
public class MainController {

	@Autowired
    private UserService userService;
	
	   //Get All Users
    @GetMapping("/user/all")
    public ResponseEntity<List<Map<String, String>>> findAll(){

        List<User> usuarios = userService.findAll();

        List<Map<String, String>> usuariosReducidos = new ArrayList<>();

        for (User usuario : usuarios) {
            Map<String, String> datosReducidos = new HashMap<>();
            datosReducidos.put("username", usuario.getUsername());
            datosReducidos.put("email", usuario.getEmail());
            datosReducidos.put("hiredate", usuario.getHiredate());
            datosReducidos.put("role", usuario.getRol());
            //sin psswrd
            usuariosReducidos.add(datosReducidos);
        }

        return new ResponseEntity<>(usuariosReducidos, HttpStatus.OK);
    }

	
	 //Post auth
    @PostMapping("/auth/signin")
    public ResponseEntity<?> signUP(@RequestBody User usuario){
            User persona=new User();
            persona.setUsername(usuario.getUsername());
            persona.setPassword(usuario.getPassword());
            User userfound = userService.findOneByIdentifier(persona.getUsername());
            
            User passwordFound = userService.findPassword(persona.getPassword()+"#@xyz");
            if(userfound == null) {
                return new ResponseEntity<>("El Usuario No Existe",HttpStatus.NOT_FOUND);
            }
            if(passwordFound == null) {
                return new ResponseEntity<>("Password incorrecta",HttpStatus.NOT_FOUND);
            }
            SigninDTO responseDTO = new SigninDTO();
            responseDTO.setUsername(userfound.getUsername());
            responseDTO.setEmail(userfound.getEmail());
            responseDTO.setRole(userfound.getRol());
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    
    //Post new user
    @PostMapping("/user")
    public ResponseEntity<?> registroUsuario(@RequestBody User usuario){
        
        User persona=new User();
        persona.setID(usuario.getID());
        persona.setUsername(usuario.getUsername());
        persona.setPassword(usuario.getPassword()+"#@xyz");
        persona.setEmail(usuario.getEmail());
        persona.setName(usuario.getPassword());
        persona.setRol(usuario.getRol());
        persona.setHiredate(usuario.getHiredate());
        persona.setStatus(usuario.getStatus());
        
        if (persona == null || persona.getUsername() == null || persona.getUsername().isEmpty()) {
            return new ResponseEntity<>("Nombre es campo obligatorio", HttpStatus.BAD_REQUEST);
        }
        User userfound = userService.findOneByIdentifier(persona.getUsername());
        if (userfound != null) {
            return new ResponseEntity<>("Este username ya esta utilizado", HttpStatus.CONFLICT);
        }

        User userfound1 = userService.findOneByIdentifier(persona.getUsername());
        if(userfound1 != null || persona.getUsername() == null || persona.getUsername() == "") {
            return new ResponseEntity<>("User Repetido",HttpStatus.NOT_FOUND);
        }
        userService.guardarunUser(persona);
        return new ResponseEntity<>(persona,HttpStatus.OK);
    }
    
    
    @PatchMapping("/users/change-password")
    public ResponseEntity<String> updateUserPassword(@RequestBody UpdatePasswordDTO updatePasswordRequest) {
        String identifier = updatePasswordRequest.getIdentifier();
        String currentPassword = updatePasswordRequest.getCurrentPassword()+"#@xyz";
        String newPassword = updatePasswordRequest.getNewPassword()+"#@xyz";

        // search user con el identificador
        User user = userService.findOneByIdentifier(identifier);

        // verificar que el user existe y la contrasenia esta bien
        if (user == null || !user.getPassword().equals(currentPassword)) {
            return ResponseEntity.badRequest().body("Usuario no encontrado o contraseña actual incorrecta.");
        }

        // actualizar la passwrd
        user.setPassword(newPassword);
        userService.updateUser(user);

        return ResponseEntity.ok("Contraseña actualizada exitosamente.");
    }
}
