package com.gustilandia.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import com.gustilandia.backend.dto.DTOVentaPorCliente;
import com.gustilandia.backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update cliente set id_estado = 2 where id_cliente =:id",nativeQuery = true)
    int deleteCliente(@Param("id") Long id);
    
    @Query(value = "select count(*) from cliente where numero_documento_identidad =:dni",nativeQuery = true)
    int validateDNI(@Param("dni") String dni);

    @Query(value = "select count(*) from cliente where correo =:email",nativeQuery = true)
    int validateEmail(@Param("email") String email);
    
    Optional<Cliente> findByCorreo(String correo);
    

    // @Query(value = "select count(*) from cliente where correo =:email and numero_documento_identidad =:dni",nativeQuery = true)
    // int ValidateDNIandEmail(@Param("email") String email, @Param("dni") String dni);

}
