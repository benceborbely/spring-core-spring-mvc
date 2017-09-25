package guru.springframework.services;

import guru.springframework.domain.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private Map<Integer, Product> products;

    public ProductServiceImpl() {
        loadProducts();
    }

    @Override
    public List<Product> listAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductById(Integer id) {
        return products.get(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        if (product == null) {
            throw new RuntimeException("Product cannot be null.");
        }

        if (product.getId() == null) {
            product.setId(getNextKey());
        }

        products.put(product.getId(), product);

        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        this.products.remove(id);
    }

    private Integer getNextKey() {
        return Collections.max(products.keySet()) + 1;
    }

    private void loadProducts() {
        products = new HashMap<>();

        Product product1 = new Product();
        product1.setId(1);
        product1.setDescription("Product 1");
        product1.setImageUrl("product1.jpg");
        product1.setPrice(new BigDecimal(50));
        products.put(1, product1);

        Product product2 = new Product();
        product2.setId(2);
        product2.setDescription("Product 2");
        product2.setImageUrl("product2.jpg");
        product2.setPrice(new BigDecimal(65));
        products.put(2, product2);

        Product product3 = new Product();
        product3.setId(3);
        product3.setDescription("Product 3");
        product3.setImageUrl("product3.jpg");
        product3.setPrice(new BigDecimal(42));
        products.put(3, product3);
    }

}
