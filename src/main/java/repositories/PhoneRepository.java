package repositories;

import entites.Customer;
import entites.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    // Custom query to find all phone contacts for a specific customer
    List<Phone> findByCustomer(Customer customer);
}
