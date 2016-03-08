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
        
        DefaultSubMenu customer = new DefaultSubMenu("Customer");

        item = new DefaultMenuItem("Cadastre");
        item.setOutcome("customerCadastre");
        item.setIcon("fa fa-pencil-square-o");
        customer.addElement(item);

        item = new DefaultMenuItem("Reports");
        item.setUrl("#");
        item.setIcon("fa fa-file-text-o");
        customer.addElement(item);

        model.addElement(customer);

        DefaultSubMenu products = new DefaultSubMenu("Products");

        item = new DefaultMenuItem("Cadastre");
        item.setUrl("#");
        item.setIcon("fa fa-shopping-cart");
        products.addElement(item);

        item = new DefaultMenuItem("Reports");
        item.setUrl("#");
        item.setIcon("fa fa-file-text-o");
        products.addElement(item);

        model.addElement(products);
        
        DefaultSubMenu settings = new DefaultSubMenu("Settings");
        settings.setIcon("fa fa-shopping-cart");
        
        item = new DefaultMenuItem("System");
        item.setUrl("#");
        item.setIcon("fa fa-shopping-cart");
        settings.addElement(item);

        model.addElement(settings);
    }

    public MenuModel getModel() {
        return model;
    }

}
