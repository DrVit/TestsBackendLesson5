package ru.geekbrains.java4.lesson6;


import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.java4.lesson6.db.Main;
import ru.geekbrains.java4.lesson6.db.dao.CategoriesMapper;
import ru.geekbrains.java4.lesson6.db.dto.Product;
import ru.geekbrains.java4.lesson6.db.enums.Category;
import ru.geekbrains.java4.lesson6.db.model.CategoriesExample;
import ru.geekbrains.java4.lesson6.db.model.Products;
import ru.geekbrains.java4.lesson6.db.model.ProductsExample;

import java.util.List;
import java.util.Objects;

public class CategorySelectTests extends Main {

    @Test
    void selectCategoryTest() {
        for(long i=1; i <= countCategory; i++) {
            ProductsExample example = new ProductsExample();
            example.createCriteria().andCategory_idEqualTo(i);
            List<Products> productsList = productsMapper.selectByExample(example);
            productsList.forEach(e -> System.out.println("Selected product cat. " + e.getCategory_id() +" is " + e.getTitle()));
            System.out.println("------------------------------------");
        }

    }

}
