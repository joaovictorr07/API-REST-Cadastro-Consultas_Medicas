package com.teste.demo.repository;


/*import java.util.List;*/

/*import java.util.Date;*/



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*import com.teste.demo.Dtos.CadastroDto;*/
import com.teste.demo.entity.CadastroEntity;

@Repository
public interface CadastroRepository extends JpaRepository<CadastroEntity, Long> {}
