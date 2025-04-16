package api.product.service;

import api.product.ProductApplication;
import api.product.dto.ProductReq;
import api.product.dto.ProductResponse;
import api.product.entity.Category;
import api.product.entity.Product;
import api.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductResponse productSave(ProductReq productRequest){

        Product product = new Product(productRequest.getName(),
                productRequest.getDescription(),
                1,
                Category.valueOf(productRequest.getCategory()),
                productRequest.getPrice());
        try{
            product = productRepository.save(product);

            return new ProductResponse(product.getName(),product.getDescription(), product.getPrice());
        }catch (Exception e){
            return null;
        }

    }

    public List<ProductResponse> getAllProduct(){

        List<Product> productList = productRepository.findAll();

        List<ProductResponse> productResponsesList =  new ArrayList<>();

        for(int i = 0; i < productList.size(); i++){

            Product product = productList.get(i);

            productResponsesList.add(new ProductResponse(product.getName(),
                    product.getDescription(),
                    product.getPrice()));

        }

        return productResponsesList;

    }

    public void deleteAllProduct(){
        productRepository.deleteAll();
    }
}
