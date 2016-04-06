package rs.uns.dmi.ulf.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.uns.dmi.ulf.data.entities.UlfUser;

/**
 * Created by Nikola on 6.4.2016.
 */
@Repository
public interface UserRepository extends JpaRepository<UlfUser,Integer> {
    
    UlfUser findByUserName(String username);
}
