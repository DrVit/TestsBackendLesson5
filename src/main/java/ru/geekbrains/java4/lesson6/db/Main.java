package ru.geekbrains.java4.lesson6.db;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import ru.geekbrains.java4.lesson6.db.dao.CategoriesMapper;
import ru.geekbrains.java4.lesson6.db.dao.ProductsMapper;
import ru.geekbrains.java4.lesson6.db.dto.Product;
import ru.geekbrains.java4.lesson6.db.model.CategoriesExample;
import ru.geekbrains.java4.lesson6.db.service.ProductService;
import ru.geekbrains.java4.lesson6.db.util.RetrofitUtils;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class Main {

    public Integer productId;
    public String productTitle;
    public Integer productPrice;
    public static ProductsMapper productsMapper;
    public static ProductService productService;
    public Faker faker = new Faker();
    public static long countCategory;


    @BeforeAll
    static void beforeAll() throws IOException {
        productsMapper = Main.getProductsMapper("mybatis-config.xml");
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
        String resource = "mybatis-config.xml";
        try {
            SqlSession session = getSqlSession(resource);

            CategoriesMapper categoriesMapper = session.getMapper(CategoriesMapper.class);

            log.info("categories number: {}", categoriesMapper.countByExample(new CategoriesExample()));

            //Products

            ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);


            countCategory = categoriesMapper.countByExample(new CategoriesExample());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    public static void main(String[] args) {
//        String resource = "mybatis-config.xml";
//        try {
//            SqlSession session = getSqlSession(resource);
//
//            CategoriesMapper categoriesMapper = session.getMapper(CategoriesMapper.class);
//
//            log.info("categories number: {}", categoriesMapper.countByExample(new CategoriesExample()));
//
//            //Products
//
//            ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
//            Products selectedProduct = productsMapper.selectByPrimaryKey(3L);
//            System.out.printf("product with id = 3 is %s", selectedProduct.getTitle());
//
//            System.out.println("6th product price " + productsMapper.selectByPrimaryKey(6L).getPrice());
//            Products newProduct = new Products();
//            newProduct.setPrice(100000);
//            newProduct.setId(6L);
//            newProduct.setCategory_id(1L);
//            newProduct.setTitle("Bread");
//
//
//            ProductsExample example = new ProductsExample();
//            example.createCriteria().andIdEqualTo(6L);
//
//            productsMapper.updateByExample(newProduct, example);
//            System.out.println("6th product new price " + productsMapper.selectByPrimaryKey(6L).getPrice());

//            ProductsExample example = new ProductsExample();
//            example.createCriteria().andTitleLike("Test%");
//            List<Products> productsList = productsMapper.selectByExample(example);
//            productsList.forEach(e-> System.out.println(e.getTitle()));
//            productsMapper.deleteByExample(example);
//            productsMapper.selectByExample(new ProductsExample())
//                    .forEach(el -> System.out.println(el.getTitle()));


//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//    }


    public static CategoriesMapper getCategoriesMapper(String resource) throws IOException {
        SqlSession session = getSqlSession(resource);
        return session.getMapper(CategoriesMapper.class);
    }

    public static ProductsMapper getProductsMapper(String resource) throws IOException {
        SqlSession session = getSqlSession(resource);
        return session.getMapper(ProductsMapper.class);
    }

    private static SqlSession getSqlSession(String resource) throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession(true);
        return session;
    }


}
