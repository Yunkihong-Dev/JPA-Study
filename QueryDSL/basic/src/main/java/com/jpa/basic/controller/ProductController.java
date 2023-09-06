package com.jpa.basic.controller;

import com.jpa.basic.domain.ProductDTO;
import com.jpa.basic.entity.Product;
import com.jpa.basic.exception.NoProductException;
import com.jpa.basic.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
public class ProductController {
    private final ProductService productService;
    @GetMapping("write")
    public void write( Model model){
        model.addAttribute ( "product", new ProductDTO());
    }
//   상품추가
    @PostMapping("write")
    public RedirectView write(ProductDTO productDTO){
        productService.write(productDTO);
        return new RedirectView("/product/list");
    }


    //    상품 목록
    @GetMapping("list")
    public void getList(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        final Page<Product> pagination = productService.getList(pageable);
        model.addAttribute("pagination", pagination);
    }
//  상품 상세보기
    @GetMapping("detail/{id}")
    public void getDetail(@PathVariable("id") Long id, Model model){
        final Product product = productService.getDetail(id).orElseThrow(()->{throw new NoProductException("상품없음");
        });
        model.addAttribute("product", product);
    }




}