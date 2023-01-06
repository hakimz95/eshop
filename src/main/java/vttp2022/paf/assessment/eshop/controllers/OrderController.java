package vttp2022.paf.assessment.eshop.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;
import vttp2022.paf.assessment.eshop.respositories.CustomerRepository;
import vttp2022.paf.assessment.eshop.respositories.OrderRepository;

@RestController
@RequestMapping(path = "/api/order", 
				produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	//TODO: Task 3
	@Autowired 
	private CustomerRepository customerRepo;

	@Autowired 
	private OrderRepository orderRepo;

	@PostMapping
	public ResponseEntity<String> getOrder(@RequestBody Map<String, Object> payload) {
		String name = (String) payload.get("name");

		//Query the database for the name
		Optional<Customer> customer = customerRepo.findCustomerByName(name);
		final List<Customer> searchedCustomer = new LinkedList<>();

		JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
		for (Customer c: searchedCustomer) {
			arrBuilder.add(c.toJSON());
		}
		JsonArray result = arrBuilder.build();

		//System.out.println(result);

		JsonObject error = Json.createObjectBuilder()
			.add("error", "Customer "+name+" not found")
			.build();

		// Generate an order id
        String orderId = UUID.randomUUID().toString().substring(0, 8);

		System.out.printf("Issuing order ID %s for %s\n", orderId, name);

		if(customer.equals(Optional.empty())) {
			return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.contentType(MediaType.APPLICATION_JSON)
				.body(error.toString());
		}

		return ResponseEntity
			.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(result.toString());
	}

}
