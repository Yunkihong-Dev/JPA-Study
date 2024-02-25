package com.jpa.basic.service;

import com.jpa.basic.domain.ProductDTO;
import com.jpa.basic.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.Optional;

public interface ProductService {
    public void write(ProductDTO productDTO);

    public Page<Product> getList(Pageable pageable);
//    상품 목록 더보기, 무한 스크롤 처리
    public Slice<Product> getListBySlice (Pageable pageable);

    public Optional<Product> getDetail(Long id);

    public void update(ProductDTO productDTO);

    public void delete(Long id);

    default Product toEntity(ProductDTO productDTO){
       return Product.builder().id(productDTO.getId())
        .productName(productDTO.getProductName())
        .productPrice(productDTO.getProductPrice())
        .productStock(productDTO.getProductStock())
        .build();
    }
}
