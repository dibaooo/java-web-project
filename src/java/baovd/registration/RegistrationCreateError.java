/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baovd.registration;

/**
 *
 * @author Asus
 */
public class RegistrationCreateError {
    private String usernameLengthErr;
    private String passwordLengthErr;
    private String fullNameLengthErr;
    private String confirmNotMatches;
    private String userNameisExisted;

    public RegistrationCreateError() {
    }

    public RegistrationCreateError(String usernameLengthErr, String passwordLengthErr, String fullNameLengthErr, String confirmNotMatches, String userNameisExisted) {
        this.usernameLengthErr = usernameLengthErr;
        this.passwordLengthErr = passwordLengthErr;
        this.fullNameLengthErr = fullNameLengthErr;
        this.confirmNotMatches = confirmNotMatches;
        this.userNameisExisted = userNameisExisted;
    }

    
    
    /**
     * @return the usernameLengthErr
     */
    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    /**
     * @param usernameLengthErr the usernameLengthErr to set
     */
    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
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

    /**
     * @return the fullNameLengthErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLengthErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the confirmNotMatches
     */
    public String getConfirmNotMatches() {
        return confirmNotMatches;
    }

    /**
     * @param confirmNotMatches the confirmNotMatches to set
     */
    public void setConfirmNotMatches(String confirmNotMatches) {
        this.confirmNotMatches = confirmNotMatches;
    }

    /**
     * @return the userNameisExisted
     */
    public String getUserNameisExisted() {
        return userNameisExisted;
    }

    /**
     * @param userNameisExisted the userNameisExisted to set
     */
    public void setUserNameisExisted(String userNameisExisted) {
        this.userNameisExisted = userNameisExisted;
    }
}
