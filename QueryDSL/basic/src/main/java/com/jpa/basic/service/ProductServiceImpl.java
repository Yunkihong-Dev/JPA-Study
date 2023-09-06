package com.jpa.basic.service;

import com.jpa.basic.domain.ProductDTO;
import com.jpa.basic.entity.Product;
import com.jpa.basic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void write(ProductDTO productDTO) {
    productRepository.save(toEntity(productDTO));
    }

    @Override
    public Page<Product> getList(Pageable pageable) { return productRepository.findAllWithPaging(pageable); }

    @Override
    public Optional<Product> getDetail(Long id) {
        return productRepository.findById(id);
    }


}
