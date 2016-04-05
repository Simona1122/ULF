package rs.uns.dmi.ulf.web.managedBeans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by Nikola on 5.4.2016.
 */
@ManagedBean
@ViewScoped
public class NavigationManagedBean {

    enum DisplayedView {
        REGISTRATION
    }

    private DisplayedView displayedView;

    @PostConstruct
    void init() {
        displayedView = DisplayedView.REGISTRATION;
    }

    public Boolean showingRegistration() {
        return displayedView == DisplayedView.REGISTRATION;
    }

    public void displayRegistration() {
        displayedView = DisplayedView.REGISTRATION;
    }

    public DisplayedView getDisplayedView() {
        return displayedView;
    }

    public void setDisplayedView(DisplayedView displayedView) {
        this.displayedView = displayedView;
    }
}
