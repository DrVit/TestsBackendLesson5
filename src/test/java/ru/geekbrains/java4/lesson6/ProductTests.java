package ru.geekbrains.java4.lesson6;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.java4.lesson6.db.Main;
import ru.geekbrains.java4.lesson6.db.dto.Product;
import ru.geekbrains.java4.lesson6.db.enums.Category;
import ru.geekbrains.java4.lesson6.db.model.Products;
import ru.geekbrains.java4.lesson6.db.model.ProductsExample;

import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductTests extends Main {
    Product product;

    @BeforeEach
    @SneakyThrows
    void setUp() {

// Create Product
        product = new Product()
                .withCategoryTitle(Category.FOOD.title)
                .withTitle(faker.food().ingredient())
//                .withId((int) (Math.random() * 10000))
                .withPrice((int) (Math.random() * 10000));
// Get Id created product
        productId = Objects.requireNonNull(
                productService.createProduct(product)
                        .execute()
                        .body()
        )
                .getId();
    }

    @Test
    @SneakyThrows
    void updateSelectProductTest() {
        System.out.println("Old product: " +  productsMapper.selectByPrimaryKey(productId.longValue()).getTitle()
                +" price: " + productsMapper.selectByPrimaryKey(productId.longValue()).getPrice());
// Update product
        Products newProduct = new Products();
        newProduct.setPrice(100000);
        newProduct.setId(productId.longValue());
        newProduct.setTitle(faker.food().ingredient());
        newProduct.setCategory_id(1L);

        ProductsExample example = new ProductsExample();
           example.createCriteria().andIdEqualTo(productId.longValue());
        productsMapper.updateByExample(newProduct, example);
        System.out.println("Updated product: " + productsMapper.selectByPrimaryKey(productId.longValue()).getTitle()
               +" new price:" + productsMapper.selectByPrimaryKey(productId.longValue()).getPrice());

// Delete created/modified product
        Integer sizeBefore = productsMapper.selectByExample(new ProductsExample()).size();
        productsMapper.deleteByPrimaryKey(productId.longValue());
        assertThat(productsMapper.selectByExample(new ProductsExample()).size(), equalTo(sizeBefore - 1));
    }

}
