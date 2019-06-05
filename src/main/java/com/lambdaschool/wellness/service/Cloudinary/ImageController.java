package com.lambdaschool.wellness.service.Cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/image")
@CrossOrigin("http://localhost:3000")
public class ImageController {
    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", System.getenv("CLOUDINARY_CLOUD_NAME"),
            "api_key", System.getenv("CLOUDINARY_API_KEY"), "api_secret", System.getenv("CLOUDINARY_API_SECRET_KEY")));

    @PostMapping("/signed-upload")
    public Map uploadSignedImage(@RequestBody Image data) throws IOException {
        // System.out.println("BASE64IMAGE DATA: "+ data.getBase64ImageData());
        // System.out.println("FILE PARAMS DATA: "+ data.getFileParams().toString());
        Map result = cloudinary.uploader().upload(data.getBase64ImageData(), data.getFileParams());
        return result;
    }
}
