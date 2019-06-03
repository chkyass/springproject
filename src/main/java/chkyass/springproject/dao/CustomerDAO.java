package chkyass.springproject.dao;

import chkyass.springproject.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getCustomers();

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int id);

    void deleteCustomer(int id);
}
