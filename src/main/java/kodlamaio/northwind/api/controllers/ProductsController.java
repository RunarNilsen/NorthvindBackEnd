package kodlamaio.northwind.api.controllers;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.business.abstracts.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import kodlamaio.northwind.core.entities.AuthenticationRequest;
import kodlamaio.northwind.core.entities.AuthenticationResponse;
import kodlamaio.northwind.core.utilities.JwtUtil;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin // bu disaridan data alinmasina izin verir mesela React tarafindfan
public class ProductsController {

	@Autowired
	private ProductService productService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserService userService;

	public ProductsController() {

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


	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}


		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}




}


