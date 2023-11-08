package com.joaosilveira.workshopmongo.services;

import com.joaosilveira.workshopmongo.dtos.UserDTO;
import com.joaosilveira.workshopmongo.entities.User;
import com.joaosilveira.workshopmongo.repositories.UserRepository;
import com.joaosilveira.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
            return userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not Found"));
    }

    public User insert(User obj) {
        return userRepository.insert(obj);
    }


    public void delete(String id) {
        findById(id); // chama o tratamento de erros acima
        userRepository.deleteById(id);
    }

    public User update(User obj) {
        User newObj = userRepository.findById(obj.getId()).get();
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }


    public User fromDTO(UserDTO objDTO) {

        return new User(
                objDTO.id(),
                objDTO.name(),
                objDTO.email()
        );
    }




}
