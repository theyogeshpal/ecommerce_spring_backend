package com.example.ecommerce_backend.controllers;

import com.example.ecommerce_backend.models.Products;
import com.example.ecommerce_backend.repositories.ProductRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:5173"})
@RequestMapping("/api")
public class ProductController {


    ProductRepository pr;
    public ProductController(ProductRepository pr)
    {
        this.pr = pr;
    }

    @PostMapping("/product")
    public Products AddProduct(
            @RequestParam("category") String category,
            @RequestParam("name") String name,
            @RequestParam("mrp") String mrp,
            @RequestParam("saleprice") String saleprice,
            @RequestParam("description") String description,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2
     ) throws IOException
    {


        //upload first file
        String folder = "upload/";
        String filename1 = System.currentTimeMillis() + "_" + image1.getOriginalFilename();
        Path path1 = Paths.get(folder,filename1);
        Files.write(path1, image1.getBytes());

        //upload second file
        String filename2 = System.currentTimeMillis() + "_" + image2.getOriginalFilename();
        Path path2 = Paths.get(folder,filename2);
        Files.write(path2, image2.getBytes());

        //create model object
        Products data = new Products();

        data.setCategory(category);
        data.setName(name);
        data.setMrp(mrp);
        data.setSaleprice(saleprice);
        data.setDescription(description);
        data.setImage1(filename1);
        data.setImage2(filename2);

        return pr.save(data);
    }

    @GetMapping("/product")
    public List<Products> showAllProducts()
    {
        return pr.findAll();
    }

    @GetMapping("/product/{id}")
    public Products getSingleProduct(@PathVariable Long id)
    {
        return pr.findById(id).orElse(null);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> DeleteProduct(@PathVariable Long id)
    {
        pr.deleteById(id);

        return ResponseEntity.ok(Map.of(
           "status","success",
           "message","Product Deleted Successfully"
        ));
    }

}
