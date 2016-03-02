package prototype.java.jsf.project1.controllers;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import prototype.java.jsf.project1.model.CustomerTO;

@ViewScoped
@Named
public class CustomerController implements Serializable {

    private static final long serialVersionUID = 1L;
    private CustomerTO customer;

    public CustomerController() {
        customer = new CustomerTO();
    }

    public void save() {
        System.out.println(customer.toString());
    }

    public CustomerTO getCustomer() {
        return customer;
    }

}
