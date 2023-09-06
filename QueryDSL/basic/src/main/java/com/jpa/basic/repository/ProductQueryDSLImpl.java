package com.jpa.basic.repository;

import com.jpa.basic.entity.Product;
import com.jpa.basic.entity.QProduct;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.jpa.basic.entity.QProduct.product;

public class ProductQueryDSLImpl implements ProductQueryDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Product> findAll() {
//        static import를 통해서 선언 없이 바로 product를 사용할 수 있다.
//        select()에 파라미터를 비워놓을 수 없다.
//        Fetch는 여러개의 정보를 가져올 때 쓰고
        final List<Product> products = query.select(product).from(product).fetch();

        return products;
    }

    @Override
    public Page<Product> findAllWithPaging(Pageable pageable) {
         final List<Product> products = query.select(product)
                .from(product).
                orderBy(product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

// fetchone은 하나의 정보를 가져올 때 쓴다.
         final Long count = query.select(product.count()).from(product).fetchOne();
        return new PageImpl<>(products,pageable, count);
    }

    @Override
    public void updateById(Product product) {
        query.update(QProduct.product).set(QProduct.product.productName,product.getProductName())
        .where(QProduct.product.id.eq(product.getId())).execute();
    }

    @Override
    public void updatePrice() {
        query.update(product)
                .set(product.productPrice, product.productPrice.doubleValue().multiply(1.1).intValue())
                .where(product.productPrice.loe(
                        JPAExpressions.select(product.productPrice.avg()).from(product)
                )).execute();
    }

    @Override
    public List<Product> selectProductByNameContainsKeyword(String keyWord) {
        List<Product> itemsContainsKeywordInName = query.selectFrom(product)
                .where(product.productName.contains(keyWord)).fetch();
        return itemsContainsKeywordInName;

    }

    @Override
    public double selectAvgPrice() {
        return JPAExpressions.select(product.productPrice.avg()).from(product).fetchOne();
    }

    public void deleteByProductName(String productName) {
        query.delete(product).where(product.productName.eq(productName)).execute();
    }
}
