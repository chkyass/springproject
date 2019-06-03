package chkyass.springproject.controller;

import chkyass.springproject.entity.Customer;
import chkyass.springproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // need to inject the customer dao
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> theCustomers = customerService.getCustomers();
        model.addAttribute("customers", theCustomers);

        return "list-customers";
    }

    @GetMapping("showFormForAdd")
    public String showFormAdd(Model model) {
        // create model attribute to bind form data
        Customer theCustomer = new Customer();

        model.addAttribute("customer", theCustomer);
        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {

        customerService.saveCustomer(customer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int id,
                                  Model model) {

        Customer customer = customerService.getCustomer(id);

        model.addAttribute("customer", customer);

        // send over to our form
        return "customer-form";

    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id) {

        // delete the customer
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }
}
