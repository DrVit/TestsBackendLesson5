package ru.geekbrains.java4.lesson6;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.java4.lesson6.db.Main;
import ru.geekbrains.java4.lesson6.db.dto.Product;
import ru.geekbrains.java4.lesson6.db.enums.Category;
import ru.geekbrains.java4.lesson6.db.model.Products;
import ru.geekbrains.java4.lesson6.db.model.ProductsExample;

import java.util.List;
import java.util.Objects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductSelectTests extends Main {

    String titleString;
    Product product;

    @BeforeEach
    @SneakyThrows
    void setUp() {
               product = new Product()
                .withCategoryTitle(Category.FOOD.title)
                .withTitle(faker.food().ingredient())
//                .withId((int) (Math.random() * 10000))
                .withPrice((int) (Math.random() * 10000));
        //create Product

        titleString = Objects.requireNonNull(
                productService.createProduct(product)
                        .execute()
                        .body()
        )
                .getTitle();
    }

    @Test
    void selectTitleTest() {

        Integer sizeBefore = productsMapper.selectByExample(new ProductsExample()).size();
        productsMapper.selectByExample(new ProductsExample())
                .forEach(el-> System.out.println(el.getTitle()));
        System.out.println("--------------");
        ProductsExample example = new ProductsExample();
        example.createCriteria().andTitleLike(titleString);
        List<Products> productsList = productsMapper.selectByExample(example);
        productsList.forEach(e-> System.out.println("Selected product is " + e.getTitle()));
  //            System.out.println("6th product new price " + productsMapper.selectByPrimaryKey(6L).getPrice());

        productsMapper.deleteByExample(example);
        productsMapper.selectByExample(new ProductsExample())
                .forEach(el-> System.out.println(el.getTitle()));
        assertThat(productsMapper.selectByExample(new ProductsExample()).size(), equalTo(sizeBefore - 1));

    }

}
