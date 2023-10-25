package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import entites.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Custom query to find a customer by name
    Optional<Customer> findByName(String name);
}
