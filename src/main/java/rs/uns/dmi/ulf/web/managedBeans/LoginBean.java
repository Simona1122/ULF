package rs.uns.dmi.ulf.web.managedBeans;

import rs.uns.dmi.ulf.data.entities.UlfUser;
import rs.uns.dmi.ulf.data.repositories.UserRepository;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Created by nikola on 11-Apr-16.
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    @ManagedProperty("#{userRepository}")
    UserRepository userRepository;

    UlfUser loggedInUser;

    String loginMessage;

    String username;
    String password;

    @PostConstruct
    void init() {
        loggedInUser = null;
        loginMessage = null;
    }

    public void logIn() {
        UlfUser user = userRepository.findByUserName(username);
        Boolean ok = user != null;
        if (ok) {
            ok = user.getPassword().equals(password);
        }
        if (ok) {
            loggedInUser = user;
            loginMessage = null;
        } else {
            loginMessage = "Pogresno ime ili lozinka.";
        }
    }

    public void logOut(){
        loggedInUser = null;
    }

    public Boolean someoneIsLoggedIn() {
        return loggedInUser != null;
    }

    public UlfUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(UlfUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
