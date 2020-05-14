package org.jianyi.spring.test.webfluxapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@SpringBootApplication
@EnableWebFlux
@RestController
public class WebfluxappApplication {

	@Autowired
	private FooService fooService;

	public static void main(String[] args) {
		SpringApplication.run(WebfluxappApplication.class, args);
	}

	@PostMapping("/foo")
	public Mono<FooDTO> add(@RequestBody FooDTO foo) {
		return fooService.save(foo);
	}

	@GetMapping("/foo/{id}")
	public Mono<FooDTO> get(@PathVariable Long id) {
		return fooService.getFoo(id);
	}

	@GetMapping("/foo")
	public Flux<FooDTO> getAll() {
		return fooService.getFoos();
	}

}
