package com.teste.demo.repository;

import java.util.Date;
/*import java.util.List;*/

/*import java.util.Date;*/



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*import com.teste.demo.Dtos.CadastroDto;*/
import com.teste.demo.entity.Cadastro;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
	
	Cadastro findBydataConsulta(Date dataConsulta);



}
