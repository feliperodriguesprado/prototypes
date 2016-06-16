package user.view.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import user.api.services.User;
import util.servicelocator.providers.BundleProvider;

@ViewScoped
@Named
public class MenuController implements Serializable {

    private static final long serialVersionUID = 1L;
    private MenuModel model;
    private User userService;

    @PostConstruct
    public void init() {

        userService = (User) BundleProvider.getBundleService(User.class);

        if (userService != null) {
            userService.test();
        }

        model = new DefaultMenuModel();
        DefaultMenuItem item = new DefaultMenuItem(" Dashboard");
        item.setIcon("fa fa-dashboard");
        item.setOutcome("dashboard");
        model.addElement(item);
        DefaultSubMenu customer = new DefaultSubMenu("Users");
        customer.setExpanded(false);
        item = new DefaultMenuItem("List");
        item.setOutcome("userList");
        item.setIcon("fa fa-users");
        customer.addElement(item);
        model.addElement(customer);
    }

    public MenuModel getModel() {
        return model;
    }

}
