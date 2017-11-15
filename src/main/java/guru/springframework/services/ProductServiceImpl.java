package guru.springframework.services;

import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Profile("map")
@Service
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    public ProductServiceImpl() {
        super();
    }

    @Override
    public List<DomainObject> getAll() {
        return super.getAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    protected void loadDomainObjects() {
        domainObjects = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setImageUrl("product1.jpg");
        product1.setPrice(new BigDecimal(50));
        domainObjects.put(1, product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("Product 2");
        product2.setImageUrl("product2.jpg");
        product2.setPrice(new BigDecimal(65));
        domainObjects.put(2, product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setDescription("Product 3");
        product3.setImageUrl("product3.jpg");
        product3.setPrice(new BigDecimal(42));
        domainObjects.put(3, product3);
    }

}
