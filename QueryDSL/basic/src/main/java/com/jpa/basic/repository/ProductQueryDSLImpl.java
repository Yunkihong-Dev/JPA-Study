package com.jpa.basic.repository;

import com.jpa.basic.entity.Product;
import com.jpa.basic.entity.QProduct;
import com.querydsl.core.types.Order;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;

import java.util.List;

import static com.jpa.basic.entity.QProduct.product;

public class ProductQueryDSLImpl implements ProductQueryDSL {
    @Autowired
    private JPAQueryFactory query;

    @Override
    public List<Product> findAll() {
//        static import를 통해서 선언 없이 바로 product를 사용할 수 있다.
//        select()에 파라미터를 비워놓을 수 없다.
//        Fetch는 여러개의 정보를 가져올 때 쓰고 싶을 때 쓴다.
        final List<Product> products = query.select(product).from(product).fetch();

        return products;
    }

    @Override
    public Slice<Product> findAllWithSlice(Pageable pageable) {
//        사용자가 요청한 페이지의 게시글 개수를 +1개 가져온다.
//        다음 페이지 유무를 알아야 화면 처리가 가능하기 때문!
        final List<Product> products = query.selectFrom(product)
                .orderBy(product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize()+1)
                .fetch();

        boolean hasNext = false;
//        정말 한 개를 더 가져왔다면, 다음페이지가 있다는 뜻이고,
//        원래 요청한 개수랑 동일하게 다음페이지가 없다는 뜻이다.
        if(products.size()>pageable.getPageSize()){
            hasNext = true;
//            실제 화면에선 요청한 개수만 필요하기에
//            다음 페이지 유무 검사를 위해 가져온 마지막 게시글은 삭제한다.
            products.remove(pageable.getPageSize());
        }
//      Slice의 hasNext()를 사용할 경우 전달한 hasNext 값이 나온다.
        return new SliceImpl<>(products,pageable,hasNext);
    }

    @Override
    public Page<Product> findAllWithPaging(Pageable pageable) {
         final List<Product> products = query.select(product)
                .from(product)
                .orderBy(product.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

//        fetchOne: 하나의 정보를 가져올 때 쓴다.
         final Long count = query.select(product.count()).from(product).fetchOne();
        return new PageImpl<>(products,pageable, count);
    }

//    @Override
//    public void updateById(Product product) {
//        query.update(QProduct.product).set(QProduct.product.productName,product.getProductName())
//        .where(QProduct.product.id.eq(product.getId())).execute();
//    }
    @Override
    public void updateById(Product product) {
        query.update(QProduct.product)
                .set(QProduct.product.productName, product.getProductName())
                .set(QProduct.product.productPrice, product.getProductPrice())
                .set(QProduct.product.productStock, product.getProductStock())
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
        List<Product> itemsContainsKeywordInName =  query.selectFrom(product)
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
