package rs.uns.dmi.ulf.web.validators;

import rs.uns.dmi.ulf.data.entities.UlfUser;
import rs.uns.dmi.ulf.data.repositories.UserRepository;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Created by Nikola on 8.4.2016.
 */
@ManagedBean
public class UniqueUsernameValidator implements Validator {

    @ManagedProperty("#{userRepository}")
    UserRepository userRepository;

    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String name = (String) value;

        UlfUser user = userRepository.findByUserName(name);

        if (user != null) {
            FacesMessage msg =
                    new FacesMessage("Zauzeto korisnicko ime.",
                            "Korisnicko ime '" + name + "' je zauzeto.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
