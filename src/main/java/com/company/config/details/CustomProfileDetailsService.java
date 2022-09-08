package com.company.config.details;


import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import com.company.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomProfileDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public CustomProfileDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        ProfileEntity profileEntity = profileRepository
                .findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Bunday foydalanuvchi topilmadi!"));

        if (profileEntity.getStatus().equals(ProfileStatus.BLOCK)) {
            log.warn("Profile Blocked phone={}", login);
            throw new BadCredentialsException("Foydalanuvchi bloklangan!");
        }

        return new CustomProfileDetails(profileEntity);
    }
}
