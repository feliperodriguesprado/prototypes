package prototype.java.jsf.project2.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@ViewScoped
@Named
public class MenuController implements Serializable {

    private static final long serialVersionUID = 1L;

    private MenuModel model;

    @PostConstruct
    public void init() {

        model = new DefaultMenuModel();

        DefaultMenuItem item = new DefaultMenuItem(" Dashboard");
        item.setIcon("fa fa-dashboard");
        item.setOutcome("dashboard");
        model.addElement(item);

        DefaultSubMenu firstSubmenu = new DefaultSubMenu("Customer");
        firstSubmenu.setExpanded(false);

        item = new DefaultMenuItem("Cadastre");
        item.setOutcome("customerCadastre");
        item.setIcon("fa fa-pencil-square-o");
        firstSubmenu.addElement(item);

        item = new DefaultMenuItem("Reports");
        item.setUrl("#");
        item.setIcon("fa fa-file-text-o");
        firstSubmenu.addElement(item);

        model.addElement(firstSubmenu);

        DefaultSubMenu secondSubmenu = new DefaultSubMenu("Products");
        firstSubmenu.setExpanded(false);
        
        item = new DefaultMenuItem("Cadastre");
        item.setUrl("#");
        item.setIcon("fa fa-shopping-cart");
        secondSubmenu.addElement(item);

        item = new DefaultMenuItem("Reports");
        item.setUrl("#");
        item.setIcon("fa fa-file-text-o");
        secondSubmenu.addElement(item);

        model.addElement(secondSubmenu);
    }

    public MenuModel getModel() {
        return model;
    }

}
