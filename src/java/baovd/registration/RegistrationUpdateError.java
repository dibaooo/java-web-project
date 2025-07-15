/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baovd.registration;

import java.io.Serializable;

/**
 *
 * @author Asus
 */
public class RegistrationUpdateError implements Serializable{
    private String passwordLengthErr;

    public RegistrationUpdateError() {
    }

    public RegistrationUpdateError(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }
    

    /**
     * @return the passwordLengthErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLengthErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }
    
    
}
