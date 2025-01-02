package com.sadds.CustomerService.repository;

import com.sadds.CustomerService.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
