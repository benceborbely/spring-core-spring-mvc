package guru.springframework.services;

import guru.springframework.domain.Customer;
import guru.springframework.domain.DomainObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {

    @Override
    public List<DomainObject> getAll() {
        return super.getAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer domainObject) {
        return (Customer) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    protected void loadDomainObjects() {
        domainObjects = new HashMap<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setFirstName("Ben");
        customer1.setLastName("Wall");
        customer1.setEmail("benwall@test.com");
        customer1.setPhoneNumber("12345678");
        customer1.setCity("Los Angeles");
        customer1.setState("California");
        customer1.setZipCode("12345");
        customer1.setAddressLine1("ABC street 156");
        customer1.setAddressLine2("EFG street 136");
        domainObjects.put(1, customer1);
    }

}
