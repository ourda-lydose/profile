package id.ac.ui.cs.advprog.profile.repository;

import id.ac.ui.cs.advprog.profile.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<User, Integer> {

}
