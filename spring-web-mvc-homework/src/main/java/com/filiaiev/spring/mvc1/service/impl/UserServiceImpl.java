package com.filiaiev.spring.mvc1.service.impl;

import com.filiaiev.spring.mvc1.dto.user.UserProfileDto;
import com.filiaiev.spring.mvc1.exception.repository.RecordNotFoundException;
import com.filiaiev.spring.mvc1.model.UserDetailsImpl;
import com.filiaiev.spring.mvc1.repository.UserRepository;
import com.filiaiev.spring.mvc1.service.UserService;
import com.filiaiev.spring.mvc1.util.SecurityUtil;
import com.filiaiev.spring.mvc1.util.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserProfileDto getUser(Long id) {
        UserDetailsImpl userDetails =
                SecurityUtil.getUserDetails(SecurityContextHolder.getContext().getAuthentication());

        if(!userDetails.getId().equals(id)) {
            throw new RecordNotFoundException();
        }

        return UserMapper.INSTANCE.toUserProfile(
                userRepository.findById(id).orElseThrow(RecordNotFoundException::new));
    }

}
