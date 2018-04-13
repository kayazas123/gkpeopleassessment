package com.gk.assessment.gkassessment.backend.persistence.repositories;

import com.gk.assessment.gkassessment.web.domain.frontend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by AYAZ on 12/04/2018.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long>{

    public User findByUsername(String username);

}
