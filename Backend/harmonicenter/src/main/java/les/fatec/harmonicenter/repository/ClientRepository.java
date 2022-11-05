package les.fatec.harmonicenter.repository;

import les.fatec.harmonicenter.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByEmail(String email);

    @Transactional
    @Query(nativeQuery = true, value =
                "SELECT " +
                    " FROM " +
                    "  _CLIENT " +
                    "WHERE " +
                    "   email = :email AND " +
                    "   password = :password ")
    Client getUserByAuthenticate(@Param("email") String email,
                                 @Param("password") String password);


    @Transactional
    Client findByEmail(String email);
}
