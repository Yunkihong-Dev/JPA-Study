package com.jpa.advanced.repository.hospital;

import com.jpa.advanced.entity.hospital.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    @Query("select p, p.owner from Pet p inner join p.owner")
    public List<Owner> findAll();

}
