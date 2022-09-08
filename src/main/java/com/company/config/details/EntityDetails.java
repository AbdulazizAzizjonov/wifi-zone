package com.company.config.details;

import com.company.entity.ProfileEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;



public class EntityDetails {

    private static CustomProfileDetails getEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomProfileDetails) authentication.getPrincipal();
    }

    public static ProfileEntity getProfile() {
        return getEntity().getProfile();
    }


}
