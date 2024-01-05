package org.zerock.mallapi.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.ProductDTO;

import java.util.UUID;

@SpringBootTest
@Log4j2
class ProductServiceTests {

    @Autowired
    ProductService productService;

    @Test
    public void testList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        PageResponseDTO<ProductDTO> result = productService.getList(pageRequestDTO);

        result.getDtoList().forEach(dto -> log.info(dto));
    }

    @Test
    public void testRegister() {

        ProductDTO productDTO = ProductDTO.builder()
                .pname("새로운 상품")
                .pdesc("신규 추가 상품")
                .price(1000)
                .build();

        productDTO.setUploadFileNames(
                java.util.List.of(
                        UUID.randomUUID() + "_" + "TEST1.jpg",
                        UUID.randomUUID() + "_" + "TEST2.jpg"
                )
        );

        productService.register(productDTO);
    }

    @Test
    public void testRead() {

        Long pno = 12L;

        ProductDTO productDTO = productService.get(pno);

        log.info(productDTO);
        log.info(productDTO.getUploadFileNames());
    }
}