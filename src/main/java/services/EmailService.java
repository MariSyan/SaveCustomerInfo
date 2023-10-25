package services;

import entites.Customer;
import entites.Email;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CustomerRepository;
import repositories.EmailRepository;

import java.util.List;

@Service
public class EmailService {
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Email addEmailContact(Long customerId, Email email) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        email.setCustomer(customer);
        return emailRepository.save(email);
    }

    public List<Email> getEmailContactsForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        return emailRepository.findByCustomer(customer);
    }
}
