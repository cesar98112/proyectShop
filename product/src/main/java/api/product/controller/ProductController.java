package api.product.controller;

import api.product.dto.ProductReq;
import api.product.dto.ProductResponse;
import api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/product")

public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> saveProductController(@RequestBody ProductReq productRequest){

        ProductResponse productResponse = productService.productSave(productRequest);

        if(productResponse != null){
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);



    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getAllProductController(){

        List<ProductResponse> productResponses = productService.getAllProduct();
        if(!productResponses.isEmpty()){
            return new ResponseEntity<>(productResponses, HttpStatus.OK);

        }
        return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);

    }


}
