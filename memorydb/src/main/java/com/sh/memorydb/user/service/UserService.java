package com.sh.memorydb.user.service;

import com.sh.memorydb.user.db.UserRepository;
import com.sh.memorydb.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity save(UserEntity userEntity) {
        //save
        return userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }

    public void delete(Long id) {
        //userRepository.delete(id);
    }

    public List<UserEntity> filterScore(int score){
        return userRepository.findAllByScoreGreaterThanEqual(score);
    }

    public List<UserEntity> filterScore(int min, int max){
        return userRepository.findAllByScoreGreaterThanEqualAndScoreLessThanEqual(min, max);
    }

    public List<UserEntity> filterScore2(int min, int max){
        //return userRepository.score(min, max);
        return userRepository.score2(min, max);
    }

}
