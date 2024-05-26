package id.ac.ui.cs.advprog.profile.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.datafaker.Faker;

import id.ac.ui.cs.advprog.profile.model.UserProfile;
import id.ac.ui.cs.advprog.profile.repository.ProfileRepository;

@Service
public class SeedService {
    @Autowired
    private ProfileRepository profileRepository;
    private static final int NUMBER_OF_PROFILE = 1000;

    public void seed() {
        @SuppressWarnings("deprecation")
        Faker faker = new Faker(new Locale("id_ID"));

        for (int i = 0; i < NUMBER_OF_PROFILE; i++) {
            String id = faker.number().digit();
            String userName = faker.name().username();
            String password = faker.random().toString();
            String role = faker.random().toString();
            String email = faker.internet().emailAddress();
            String gender = faker.gender().binaryTypes();
            String noTelp = faker.phoneNumber().cellPhone();
            String alamat = faker.address().fullAddress();
            String bio = faker.lorem().paragraph();
            Boolean isAdmin = faker.random().nextBoolean();

            if (bio.length() > 255) {
                bio = bio.substring(0, 255);
            }
            if (role.length() > 5) {
                role = role.substring(0, 5);
            }
            if (email.length() > 100) {
                email = email.substring(0, 100);
            }

            UserProfile user = new UserProfile.UserBuilder()
                    .setId(id)
                    .setUserName(userName)
                    .setPassword(password)
                    .setRole(role)
                    .setEmail(email)
                    .setGender(gender)
                    .setNoTelp(noTelp)
                    .setAlamat(alamat)
                    .setBio(bio)
                    .setIsAdmin(isAdmin)
                    .build();

            profileRepository.save(user);
        }
    }
}
