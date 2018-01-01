package reomor.voting.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import reomor.voting.model.User;
import reomor.voting.repository.UserRepository;

import java.util.List;

public class DataJpaUserRepositoryImpl implements UserRepository {
    @Autowired
    CrudUserRepository userRepository;
}
