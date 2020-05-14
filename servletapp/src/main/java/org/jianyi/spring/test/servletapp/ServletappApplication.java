package org.jianyi.spring.test.servletapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
public class ServletappApplication {

	@Autowired
	private FooService fooService;

	public static void main(String[] args) {
		SpringApplication.run(ServletappApplication.class, args);
	}

	@PostMapping("/foo")
	public ResponseEntity<FooDTO> add(@RequestBody FooDTO foo) {
		return ResponseEntity.ok(fooService.save(foo));
	}

	@GetMapping("/foo/{id}")
	public ResponseEntity<FooDTO> get(@PathVariable Long id) {
		return ResponseEntity.ok(fooService.getFoo(id));
	}

	@GetMapping("/foo")
	public ResponseEntity<List<FooDTO>> getAll() {
		return ResponseEntity.ok(fooService.getFoos());
	}

}
