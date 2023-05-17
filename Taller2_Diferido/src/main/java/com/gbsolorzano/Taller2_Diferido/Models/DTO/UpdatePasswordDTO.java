package com.gbsolorzano.Taller2_Diferido.Models.DTO;

import lombok.Data;

@Data
public class UpdatePasswordDTO {
	private String identifier;
    private String currentPassword;
    private String newPassword;
    
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
      
}
