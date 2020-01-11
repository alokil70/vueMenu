package ru.restoran.menu.controller;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.restoran.menu.domain.Food;
import ru.restoran.menu.domain.Views;
import ru.restoran.menu.repos.FoodRepo;

import javax.persistence.Access;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final FoodRepo foodRepo;
    private String UPLOAD_PATH = "upload";

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

        File convertFile = new File(UPLOAD_PATH + file.getOriginalFilename());
        try {
            convertFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream outputStream = new FileOutputStream(convertFile)) {
            outputStream.write(file.getBytes());
        } catch (Exception exe) {
            exe.printStackTrace();
        }
        return "File has uploaded successfully";
    }




/*    @PostMapping("/file")
    public String uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();
        String path = request.getServletContext().getRealPath("") + UPLOAD_PATH
                + File.separator + fileName;
    }

    private void saveFile(InputStream inputStream, String path){
        try {
            OutputStream stream = new FileOutputStream(new File(path));

        } catch (Exception e){

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
