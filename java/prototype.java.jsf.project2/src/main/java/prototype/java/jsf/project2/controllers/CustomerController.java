package prototype.java.jsf.project2.controllers;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import prototype.java.jsf.project2.models.CustomerModel;

@ViewScoped
@Named
public class CustomerController implements Serializable {

    private static final long serialVersionUID = 1L;

    private CustomerModel customerModel;

    @PostConstruct
    public void init() {
        customerModel = new CustomerModel();
    }

    public CustomerModel getCustomerModel() {
        return customerModel;
    }

    public void save() {
        System.out.println(customerModel);
    }

}
