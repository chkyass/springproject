package chkyass.springproject.rest;


import chkyass.springproject.entity.Customer;
import chkyass.springproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    // autowire the CustomerService
    @Autowired
    private CustomerService customerService;

    // add mapping for GET /customers
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    // add mapping for GET /customers/{customerId}
    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {
        Customer theCustomer = customerService.getCustomer(customerId);

        if (theCustomer == null) {
            throw new CustomerNotFoundException("Customer id not found: " + theCustomer);
        }
        return theCustomer;
    }

    // add mapping for POST / customers - add new customer
    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer theCustomer) {
        // set id to 0 to force a save of new item instead of update
        theCustomer.setId(0);

        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @PutMapping("/customers")
    public Customer updateCustomer(@RequestBody Customer theCustomer) {

        customerService.saveCustomer(theCustomer);

        return theCustomer;
    }

    @DeleteMapping("/customers/{id}")
    public Customer deleteCustomer(@PathVariable int id) {

        Customer customer = customerService.getCustomer(id);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found: " + customer);
        }
        customerService.deleteCustomer(id);

        return customer;
    }

}
