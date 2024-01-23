package com.joaosilveira.dslearn.services;


import com.joaosilveira.dslearn.dto.*;
import com.joaosilveira.dslearn.entities.Role;
import com.joaosilveira.dslearn.entities.User;
import com.joaosilveira.dslearn.projections.UserDetailsProjection;
import com.joaosilveira.dslearn.repositories.RoleRepository;
import com.joaosilveira.dslearn.repositories.UserRepository;
import com.joaosilveira.dslearn.services.exceptions.DatabaseException;
import com.joaosilveira.dslearn.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.dslearn.util.CustomUserUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserUtil customUserUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = userRepository.searchUserAndRolesByEmail(username);
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(result.get(0).getPassword());


        for (UserDetailsProjection projection: result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

    public User authenticated() {
        try {
            String username = customUserUtil.getLoggedUsername();
            return userRepository.findByEmail(username).get();

        } catch (Exception e) {
            throw new UsernameNotFoundException("Email not found");
        }
    }

    @Transactional(readOnly = true)
    public UserDTO findMe() {
        User user = authenticated();
        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> findAll(Pageable pageable) {
        return userRepository.findAllPageable(pageable);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        User user = userOptional.orElseThrow(() -> {
           throw new ResourceNotFoundException("Recurso não encontrado");
        });
        return new UserDTO(user);
    }


    @Transactional
    public UserUpdateDTO update(Long id, UserUpdateDTO dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyUserDtoToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserUpdateDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public UserInsertDTO insert(UserInsertDTO dto) {
        User user = new User();
        copyUserDtoToEntity(dto, user);

        // limpa toddas as roles do usuário
        user.getRoles().clear();
        // pega a role_operator no banco de dados usando o QUERY METHOD
        Role role = roleRepository.findByAuthority("ROLE_OPERATOR");
        // adicionando a role operator para o usuário
        user.getRoles().add(role);

        // pega a senha que o usuario digitar e transforma ela em um HASH
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        // devolve o DTO que contém também a senha
        return new UserInsertDTO(user);
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade referencial");
        }

    }



    private void copyUserDtoToEntity(UserDTO dto, User entity) {
        entity.setEmail(dto.getEmail());


        // SALVAR UMA ROLE JUNTAMENTE COM A CRIAÇÃO DE UM NOVO USUARIO
        // LIMPA AS ROLES PRÉ EXISTENTES DO USER
        // VALIDA SE O ID DE ROLE PASSADO EXISTE, SE EXISTIR APENAS PEGA ELE E RETORNA
        // SE NÃO LANÇA UM ERRO DIZENDO PARA CRIAR ESSA NOVA ROLE OU USAR ALGUM ID EXISTENTE
        entity.getRoles().clear();
            for (RoleDTO roleDTO: dto.getRoles()) {
            Role role;
            try {
                roleRepository.existsById(roleDTO.getId());
                role = roleRepository.getReferenceById(roleDTO.getId());
                entity.getRoles().add(role);
            } catch (RuntimeException e) {
                throw new ResourceNotFoundException("Id: " + roleDTO.getId() + " nao foi encontrado, crie uma nova " +
                        "role com esse id, ou use algum id existente");
            }
        }
    }

}
