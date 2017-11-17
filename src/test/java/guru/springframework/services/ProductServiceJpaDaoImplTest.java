package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpa")
public class ProductServiceJpaDaoImplTest {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void testGetAll() throws Exception {
        List<Product> products = (List<Product>) productService.getAll();

        assert products.size() == 2;
    }

    @Test
    public void testGetById() throws Exception {
        Product product = productService.getById(1);

        assert product.getId() == 1;
        assert product.getDescription().equals("Product 1");
        assert product.getImageUrl().equals("product1.jpg");
        assert product.getPrice().equals(new BigDecimal("499.99"));
    }

    @Test
    @DirtiesContext
    public void testSave() {
        String description = "Product 3";
        String imageUrl = "product3.jpg";
        BigDecimal price = new BigDecimal("29.99");

        Product productToSave = new Product();
        productToSave.setDescription(description);
        productToSave.setImageUrl(imageUrl);
        productToSave.setPrice(price);

        Product savedProduct = productService.saveOrUpdate(productToSave);

        assert savedProduct.getId() != 1 && savedProduct.getId() != 2;
        assert savedProduct.getDescription().equals(description);
        assert savedProduct.getImageUrl().equals(imageUrl);
        assert savedProduct.getPrice().equals(price);
    }

    @Test
    @DirtiesContext
    public void testUpdate() {
        Integer id = 1;
        String description = "Product 1";
        String imageUrl = "product1.jpg";
        BigDecimal newPrice = new BigDecimal("59.99");

        Product productToUpdate = productService.getById(id);
        productToUpdate.setPrice(newPrice);

        Product updatedProduct = productService.saveOrUpdate(productToUpdate);

        assert updatedProduct.getId().equals(id);
        assert updatedProduct.getDescription().equals(description);
        assert updatedProduct.getImageUrl().equals(imageUrl);
        assert updatedProduct.getPrice().equals(newPrice);
    }

    @Test
    @DirtiesContext
    public void testDelete() {
        int id = 1;
        Product originalProduct = productService.getById(id);
        int originalSize = productService.getAll().size();

        productService.delete(originalProduct.getId());

        assert originalSize == 2;
        assert productService.getAll().size() == 1;
        assert productService.getById(id) == null;
    }

}
