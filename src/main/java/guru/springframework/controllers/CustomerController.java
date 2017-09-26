package guru.springframework.controllers;

import guru.springframework.domain.Customer;
import guru.springframework.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String list(Model model) {
        model.addAttribute("customers", customerService.getAll());

        return "customers";
    }

    @RequestMapping("/customer/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));

        return "customer";
    }

    @RequestMapping("/customer/new")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());

        return "customerform";
    }

    @RequestMapping("/customer/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.getById(id));

        return "customerform";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdate(Customer customer) {
        Customer savedProduct = customerService.saveOrUpdate(customer);

        return "redirect:/customer/" + savedProduct.getId();
    }


    @RequestMapping("/customer/delete/{id}")
    public String delete(@PathVariable Integer id) {
        customerService.delete(id);

        return "redirect:/customers";
    }

}
