package services;

import entites.Customer;
import entites.Phone;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CustomerRepository;
import repositories.PhoneRepository;

import java.util.List;

@Service
public class PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Phone addPhoneContact(Long customerId, Phone phone) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        phone.setCustomer(customer);
        return phoneRepository.save(phone);
    }

    public List<Phone> getPhoneContactsForCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        return phoneRepository.findByCustomer(customer);
    }
}

