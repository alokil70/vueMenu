package ru.restoran.menu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.restoran.menu.domain.Food;
import ru.restoran.menu.domain.Views;
import ru.restoran.menu.repos.FoodRepo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("message")
public class MessageController {

    private final FoodRepo foodRepo;
    @Value("${upload.path}")
    private String UPLOAD_PATH;

    public MessageController(FoodRepo foodRepo) {
        this.foodRepo = foodRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Food> list() {
        return foodRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Food getOne(@PathVariable("id") Food food) {
        return food;
    }

    @PostMapping
    public Food create(@RequestBody Food food) {

        food.setCreationDate(LocalDateTime.now());
        return foodRepo.save(food);
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
    public Food update(
            @PathVariable("id") Food foodFromDb,
            @RequestBody Food food
    ) {
        BeanUtils.copyProperties(food, foodFromDb, "id");

        return foodRepo.save(foodFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Food food) {
        foodRepo.delete(food);
    }
}
