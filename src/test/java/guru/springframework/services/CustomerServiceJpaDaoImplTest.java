package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpa")
public class CustomerServiceJpaDaoImplTest {

    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testGetAll() {
        List<Customer> customers = (List<Customer>) customerService.getAll();

        assert customers.size() == 1;
    }

    @Test
    public void testGetById() throws Exception {
        Customer customer = customerService.getById(1);

        assert customer.getId() == 1;
        assert customer.getAddressLine1().equals("Address 1");
        assert customer.getAddressLine2().equals("Address 2");
        assert customer.getCity().equals("Budapest");
        assert customer.getEmail().equals("firstcustomer@test.com");
        assert customer.getFirstName().equals("John");
        assert customer.getLastName().equals("Doe");
        assert customer.getPhoneNumber().equals("12345678");
        assert customer.getState().equals("State");
        assert customer.getZipCode().equals("1234");
    }

    @Test
    @DirtiesContext
    public void testSave() {
        String addressLine1 = "Address 1";
        String addressLine2 = "Address 2";
        String city = "Debrecen";
        String email = "secondcustomer@test.com";
        String firstName = "Jan";
        String lastName = "Modaal";
        String phoneNumber = "12345679";
        String state = "State2";
        String zipCode = "1235";

        Customer customerToSave = new Customer();
        customerToSave.setAddressLine1(addressLine1);
        customerToSave.setAddressLine2(addressLine2);
        customerToSave.setCity(city);
        customerToSave.setEmail(email);
        customerToSave.setFirstName(firstName);
        customerToSave.setLastName(lastName);
        customerToSave.setPhoneNumber(phoneNumber);
        customerToSave.setState(state);
        customerToSave.setZipCode(zipCode);

        Customer savedCustomer = customerService.saveOrUpdate(customerToSave);

        assert savedCustomer.getId() != 1;
        assert savedCustomer.getAddressLine1().equals(addressLine1);
        assert savedCustomer.getAddressLine2().equals(addressLine2);
        assert savedCustomer.getCity().equals(city);
        assert savedCustomer.getEmail().equals(email);
        assert savedCustomer.getFirstName().equals(firstName);
        assert savedCustomer.getLastName().equals(lastName);
        assert savedCustomer.getPhoneNumber().equals(phoneNumber);
        assert savedCustomer.getState().equals(state);
        assert savedCustomer.getZipCode().equals(zipCode);
    }

    @Test
    @DirtiesContext
    public void testUpdate() {
        String newPhoneNumber = "12345677";

        Customer customerToUpdate = customerService.getById(1);
        customerToUpdate.setPhoneNumber(newPhoneNumber);

        Customer savedCustomer = customerService.saveOrUpdate(customerToUpdate);

        assert savedCustomer.getPhoneNumber().equals(newPhoneNumber);
    }

    @Test
    @DirtiesContext
    public void testDelete() {
        int id = 1;
        Customer originalCustomer = customerService.getById(id);
        int originalSize = customerService.getAll().size();

        customerService.delete(originalCustomer.getId());

        assert originalSize == 1;
        assert customerService.getAll().size() == 0;
        assert customerService.getById(id) == null;
    }

}
