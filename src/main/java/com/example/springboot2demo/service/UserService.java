package com.example.springboot2demo.service;

import com.example.springboot2demo.document.User;
import com.example.springboot2demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * user对象的service层服务
 *
 * @author Lucifer
 * @date 2018／06／20 22:27
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @do 保存/更新,在保存的过程中报错,有可能是因为id为空(因为id为主键),此时将保存的操作变为更新，查询出库中的ID并用以更新数据
     * @param user
     * @return
     */
    public Mono<User> save(User user){
        return userRepository.save(user)
                .onErrorResume(e ->
                userRepository.findByUsername(user.getUsername())
                .flatMap(oraginaluser -> {
                    user.setId(oraginaluser.getId());
                    return userRepository.save(user);
                }));
    }

    public Mono<Long> deleteByUsername(String username){
        return userRepository.deleteByUsername(username);
    }

    public Mono<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Flux<User> findAll(){
        return userRepository.findAll();
    }


}
