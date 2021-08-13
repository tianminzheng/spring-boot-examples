package com.springboot.cache.service;

import com.github.wenhao.jpa.Specifications;
import com.springboot.cache.entity.User;
import com.springboot.cache.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
@EnableCaching
@CacheConfig(cacheNames = "user-object")
public class UserService {
	
	@Autowired
    protected UserRepository userRepository;

    @CachePut(key = "#root.targetClass + '_' + #user.id")
    public void save(User user) {
    	userRepository.save(user);
    }

    @CacheEvict(key = "#root.targetClass + '_' + #id")
    public void delete(Long id) {
    	userRepository.deleteById(id);
    }
    
    @Cacheable(key = "#root.targetClass + '_' + #id")
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        Specification<User> specification = Specifications.<User>and().eq("valid", true).build();
        return userRepository.findAll(specification);
    }

    @Cacheable(value = "test", key = "#root.targetClass + '_' + #p0 + '_' + #p1")
    public Page<User> findAll(int pageSize, int pageNumber) {
        Specification<User> specification = Specifications.<User>and().eq("valid", true).build();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return userRepository.findAll(specification, pageable);
    }

}
