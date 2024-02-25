package com.jpa.basic.controller;

import com.jpa.basic.domain.ProductDTO;
import com.jpa.basic.entity.Product;
import com.jpa.basic.exception.NoProductException;
import com.jpa.basic.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
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
    public void getList(@PageableDefault(page = 0, size = 10) Pageable pageable, Model model){
        final Page<Product> pagination = productService.getList(pageable);
        model.addAttribute("pagination", pagination);
    }




//    restAPI방식 상품목록 불러오기
    @GetMapping("/api/list")
    @ResponseBody
    public Slice<Product> getList(@PageableDefault(page = 0, size = 10) Pageable pageable){
        final Slice<Product> products = productService.getListBySlice(pageable);
        log.info(String.valueOf(products.hasNext())  );

        return  productService.getListBySlice(pageable);
    }

    @GetMapping("list-async")
    public void getList(){;

    }


    // 상품 상세보기, update 진
    @GetMapping(value = {"detail/{id}","update/{id}"})
    public String getDetail(@PathVariable  Long id, Model model, HttpServletRequest httpServletRequest) {
        // 상품 서비스를 통해 상세 정보를 가져옴.

        final Product product = productService.getDetail(id)
                .orElseThrow(() -> {
                    // 상품이 없는 경우 예외를 던짐.
                    throw new NoProductException("상품없음");
                });

        // 모델에 상품 정보를 추가함.
        model.addAttribute("product", product);

        // HTTP 요청의 URI를 분석하여 뷰 경로를 생성함.
        String uri = httpServletRequest.getRequestURI();
        String viewType = uri.split("/")[2];

        log.info(viewType);
        // 생성한 뷰 경로를 반환함.
        return "/product/" + viewType;
    }

    @PostMapping("update")
    public RedirectView update( ProductDTO productDTO){
        productService.update(productDTO);
        return new RedirectView("/product/detail/"+productDTO.getId());
    }

    @DeleteMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") Long id){
        log.info("delete 들어옴");
        productService.delete(id);
        return new RedirectView("/product/list");
    }


}