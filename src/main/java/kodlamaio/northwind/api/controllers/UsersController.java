package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {
	private UserService userService;

	@Autowired
	public UsersController(final UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/mainPage")
	public String userWelcomePage() {
		return "Welcome to users page";
	}
	@PostMapping("add")
	public ResponseEntity<?> add(@Valid @RequestBody final User user) {
		final Result result = userService.add(user);

		return ResponseEntity.ok(result);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidationException(final MethodArgumentNotValidException exceptions) {
		final Map<String, String> validationErrors = new HashMap<String, String>();
		for (final FieldError fieldError : exceptions.getBindingResult().getFieldErrors())
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());

		return new ErrorDataResult<Object>(validationErrors, "Validation Errors");
	}
}
