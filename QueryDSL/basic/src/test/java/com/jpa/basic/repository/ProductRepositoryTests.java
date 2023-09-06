package com.jpa.basic.repository;


import com.jpa.basic.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest @Slf4j @Rollback(false) @Transactional
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveTest(){
        for (int i=100; i<200; i++){
        Product product = new Product();
        product.setProductName("마우스"+(i+1)+"번째 마우");
        product.setProductPrice(45000);
        product. setProductStock(55+i);
        productRepository.save(product);
        }
    }
    @Test
    public void findAllTests(){
        log.info(productRepository.findAll().toString());
    }

    @Test
    public void findAllWithPagingTest(){
        final Page<Product> productsWithPage = productRepository.findAllWithPaging(PageRequest.of(9,10));
        log.info(productsWithPage.getContent().toString());
    }

    @Test
    public void updateByIdTest(){
        Optional<Product> foundproduct = productRepository.findById(107L);
        foundproduct.ifPresent(product -> {
            product.setProductName("수정된 마우스");
            productRepository.updateById(product);

        });
    }

    @Test
    public void updatePricesTest(){
        productRepository.updatePrice();
    }

    @Test
    public void selectProductByNameContainsFiveTest(){
        productRepository.selectProductByNameContainsKeyword("5").stream().map(Product::toString).forEach(log::info);
    }

    @Test
    public void selectAvgPriceTest(){
        productRepository.selectAvgPrice();
    }

    @Test
    public void  getDetail(){
    }
}
