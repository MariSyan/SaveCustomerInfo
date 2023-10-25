package controllers;

import entites.Contact;
import entites.Customer;
import entites.Email;
import entites.Phone;
import enums.ContactType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CustomerService;
import services.EmailService;
import services.PhoneService;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private PhoneService phoneService;

    @Autowired
    private EmailService emailService;

    // Endpoint for adding a new customer
    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    }

    // Endpoint for adding a new phone contact for a customer
    @PostMapping("/{customerId}/phones")
    public ResponseEntity<Phone> addPhoneContact(@PathVariable Long customerId, @RequestBody Phone phone) {
        Phone newPhone = phoneService.addPhoneContact(customerId, phone);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPhone);
    }

    // Endpoint for adding a new email contact for a customer
    @PostMapping("/{customerId}/emails")
    public ResponseEntity<Email> addEmailContact(@PathVariable Long customerId, @RequestBody Email email) {
        Email newEmail = emailService.addEmailContact(customerId, email);
        return ResponseEntity.status(HttpStatus.CREATED).body(newEmail);
    }

    // Endpoint to get a list of all customers
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Endpoint to get customer information by ID
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customer);
    }

    // Endpoint to get all contact information for a customer
    @GetMapping("/{customerId}/contacts")
    public ResponseEntity<List<Contact>> getContactsForCustomer(@PathVariable Long customerId) {
        List<Contact> contacts = customerService.getContactsForCustomer(customerId);
        return ResponseEntity.ok(contacts);
    }

    // Endpoint to get contact information of a specific type for a customer
    @GetMapping("/{customerId}/contacts/{contactType}")
    public ResponseEntity<List<?>> getContactsByTypeForCustomer(
            @PathVariable Long customerId, @PathVariable ContactType contactType) {
        List<?> contacts = customerService.getContactsByTypeForCustomer(customerId, contactType);
        return ResponseEntity.ok(contacts);
    }
}
