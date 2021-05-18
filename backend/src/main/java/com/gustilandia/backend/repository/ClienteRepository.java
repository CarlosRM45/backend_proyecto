package com.gustilandia.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;
import com.gustilandia.backend.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update cliente set id_estado = 2 where id_cliente =:id",nativeQuery = true)
    int deleteCliente(@Param("id") Long id);
}
