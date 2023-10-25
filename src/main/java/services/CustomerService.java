package services;

import entites.Contact;
import entites.Customer;
import entites.Email;
import entites.Phone;
import enums.ContactType;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CustomerRepository;
import repositories.EmailRepository;
import repositories.PhoneRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private EmailRepository emailRepository;

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Phone addPhoneContact(Long customerId, Phone phone) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        phone.setCustomer(customer);
        return phoneRepository.save(phone);
    }

    public Email addEmailContact(Long customerId, Email email) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        email.setCustomer(customer);
        return emailRepository.save(email);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElse(null);
    }

    public List<Contact> getContactsForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        List<?> phones = phoneRepository.findByCustomer(customer);
        List<?> emails = emailRepository.findByCustomer(customer);

        List<Contact> contacts = new ArrayList<>();
        contacts.addAll((Collection<? extends Contact>) phones);
        contacts.addAll((Collection<? extends Contact>) emails);

        return contacts;
    }

    public List<?> getContactsByTypeForCustomer(Long customerId, ContactType contactType) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        if (contactType == ContactType.PHONE) {
            return phoneRepository.findByCustomer(customer);
        } else if (contactType == ContactType.EMAIL) {
            return emailRepository.findByCustomer(customer);
        } else {
            throw new IllegalArgumentException("Invalid contact type");
        }
    }

}

