package ru.restoran.menu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.restoran.menu.domain.User;
import ru.restoran.menu.repos.FoodRepo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;


@Controller
@RequestMapping("/")
public class MainController {
    private final FoodRepo foodRepo;

    @Value("${upload.path}")
    private String UPLOAD_PATH;
/*    @Value("${spring.profiles.active}")
    private String profile;*/

    public MainController(FoodRepo foodRepo) {
        this.foodRepo = foodRepo;
    }

    @GetMapping
    public String main(Model model, User user) {
        HashMap<Object, Object> data = new HashMap<>();

        user.setId("1");
        user.setName("Alex");

        if (user != null) {
            data.put("messages", foodRepo.findAll());
            data.put("profile", user);
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", true);

        return "index";
    }

    @GetMapping("download/{fileName:.+}")
    public void downloadFileAsResponse(HttpServletRequest request,
                                       HttpServletResponse response, @PathVariable("fileName") String fileName) {
        try {
            // getting the path to file
            String dir = request.getServletContext().getRealPath(UPLOAD_PATH);
            Path file = Paths.get(UPLOAD_PATH, fileName);
            if(!Files.exists(file)){
                String errorMessage = "File you are trying to download does not exist on the server.";
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
                outputStream.close();
                return;
            }
            // getting mimetype from context
            String mimeType= request.getServletContext().getMimeType(
                    file.getFileName().toString());
            if(mimeType == null){
                // Not able to detect mimetype taking default
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.addHeader("Content-Disposition", "image/webp");
            Files.copy(file, response.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
