package api.product;

import api.product.dto.ProductReq;
import api.product.dto.ProductResponse;
import api.product.entity.Product;
import api.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

	@Autowired
	private ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<ProductResponse> productList = productService.getAllProduct();

		if(!productList.isEmpty()){
			productService.deleteAllProduct();
		}

		ProductReq product1 = new ProductReq("Lenovo ThinkPad X1 Carbon","Ultrabook empresarial ligero con pantalla de 14\" y procesador Intel Core i7.",1499.00,"PORTATILES");
		ProductReq product2 = new ProductReq("iPhone 15 Pro","Smartphone de última generación con chip A17 Pro y cámara profesional.",1199.00,"MOBILES");
		ProductReq product3 = new ProductReq("Samsung Frigorífico Combi RB38T600ESA","Frigorífico combi con tecnología No Frost y eficiencia energética A++.",699.00,"ELECTRODOMESTICOS");
		ProductReq product4 = new ProductReq("HP Pavilion Desktop TP01","Ordenador de sobremesa con procesador AMD Ryzen 5, 16GB RAM y 512GB SSD.",649.00,"SOBREMESA");

		productService.productSave(product1);
		productService.productSave(product2);
		productService.productSave(product3);
		productService.productSave(product4);

	}
}
