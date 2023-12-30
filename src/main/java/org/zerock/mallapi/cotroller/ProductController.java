package org.zerock.mallapi.cotroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.mallapi.dto.ProductDTO;
import org.zerock.mallapi.util.CustomFileUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/products")
public class ProductController {

    private final CustomFileUtil fileUtil;

    @PostMapping("/")
    public Map<String, String> register(ProductDTO productDTO) {

        log.info("register" + productDTO);

        List<MultipartFile> files = productDTO.getFiles();

        List<String> uploadFileNames = fileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadFileNames);

        log.info(uploadFileNames);

        return Map.of("result", "success");
    }
}
