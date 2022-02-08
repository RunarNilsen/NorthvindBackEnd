package kodlamaio.northwind.api.controllers;

import java.util.List;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin // bu disaridan data alinmasina izin verir mesela React tarafindfan
public class ProductsController {

	private final ProductService productService;

	@Autowired
	public ProductsController(final ProductService productService) {
		this.productService = productService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody final Product product) {
		return productService.add(product);
	}

	@GetMapping("/getall")
	public DataResult<List<Product>> getAll() {
		return productService.getAll();
	}

	@GetMapping("/getallbypage")
	public DataResult<List<Product>> getAll(@RequestParam final int pageNo, @RequestParam final int pageSize) {
		return productService.getAll(pageNo, pageSize);
	}

	@GetMapping("/getallbyproductnamecontains")
	public DataResult<List<Product>> getAllByProductNameContains(@RequestParam final String productName) {
		return productService.getAllByProductNameContains(productName);
	}

	@GetMapping("/getAllProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getAllProductWithCategoryDetails() {
		return productService.getAllProductWithCategoryDetails();
	}

	@GetMapping("/getallsorted")
	public DataResult<List<Product>> getAllSorted(@RequestParam final String direction,
			@RequestParam final String propertyNames) {
		return productService.getAllSorted(direction, propertyNames);
	}

	@GetMapping("/getbyproductname")
	public DataResult<Product> getByProductName(@RequestParam final String productName) {
		return productService.getByProductName(productName);
	}

	@GetMapping("/getbyproductnameandcategoryid")
	public DataResult<Product> getByProductNameAndCategoryId(@RequestParam final String productName,
			@RequestParam final int categoryId) {
		return productService.getByProductNameAndCategoryId(productName, categoryId);
	}

}
