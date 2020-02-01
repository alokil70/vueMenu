package ru.restoran.menu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.restoran.menu.domain.Product;
import ru.restoran.menu.domain.Views;
import ru.restoran.menu.repos.ProductRepo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductRepo productRepo;

    @Value("${upload.path}")
    private String UPLOAD_PATH;

    public ProductController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Product> list() {
        return productRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Product getOne(@PathVariable("id") Product product) {
        return product;
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        product.setCreationDate(LocalDateTime.now());
        return productRepo.save(product);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload(@RequestParam("image") MultipartFile file) {

        if (file != null) {
            File uploadDir = new File(UPLOAD_PATH);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            FileOutputStream outputStream;

            try {
                outputStream = new FileOutputStream(uploadDir + "/" + resultFileName);
                outputStream.write(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return resultFileName;
        }
        return null;
    }




/*    @ResponseBody
    @RequestMapping(value = "/image-resource", method = RequestMethod.GET)
    public Resource getImageAsResource() {
        ServletContext servletContext = null;
        return new ServletContextResource(servletContext., "/WEB-INF/images/image-example.jpg");
    }*/


/*    @PostMapping("/file")
    public String uploadFile(@RequestParam("image") MultipartFile file, HttpServletRequest request) {
        try {
            String fileName = file.getOriginalFilename();
            String path = request.getServletContext().getRealPath("")
                    + UPLOAD_PATH + File.separator + fileName;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private void saveFile(InputStream inputStream, String path) {
        try {
            OutputStream stream = new FileOutputStream(new File(path));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @PutMapping("{id}")
    public Product update(
            @PathVariable("id") Product productFromDb,
            @RequestBody Product product
    ) {
        BeanUtils.copyProperties(product, productFromDb, "id");

        return productRepo.save(productFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Product product) {
        productRepo.delete(product);
    }
}
