package guru.springframework.bootstrap;

import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.services.CustomerService;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;

    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadProducts();
        loadCustomers();
    }

    public void loadProducts() {
        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("499.99"));
        product1.setImageUrl("product1.jpg");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("299.99"));
        product2.setImageUrl("product2.jpg");
        productService.saveOrUpdate(product2);
    }

    public void loadCustomers() {
        Customer customer1 = new Customer();
        customer1.setAddressLine1("Address 1");
        customer1.setAddressLine2("Address 2");
        customer1.setCity("Budapest");
        customer1.setEmail("firstcustomer@test.com");
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setPhoneNumber("12345678");
        customer1.setState("State");
        customer1.setZipCode("1234");
        customerService.saveOrUpdate(customer1);
    }

}
