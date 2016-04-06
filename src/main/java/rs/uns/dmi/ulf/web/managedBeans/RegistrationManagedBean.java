package rs.uns.dmi.ulf.web.managedBeans;

import rs.uns.dmi.ulf.data.entities.UlfUser;
import rs.uns.dmi.ulf.data.repositories.UserRepository;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by Nikola on 6.4.2016.
 */
@ManagedBean
@ViewScoped
public class RegistrationManagedBean {

    @ManagedProperty("#{userRepository}")
    UserRepository userRepository;

    String username;
    String password;
    String repeatPassword;
    String message;

    public void register(){
        UlfUser potentialDouble = userRepository.findByUserName(username);
        if (potentialDouble == null){
            potentialDouble = new UlfUser();
            potentialDouble.setUserName(username);
            potentialDouble.setPassword(password);
            potentialDouble.setAdmin(false);
            userRepository.save(potentialDouble);
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
