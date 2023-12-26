package com.joaosilveira.challengeauthsecurity.services;


import com.joaosilveira.challengeauthsecurity.dto.UserInsertDTO;
import com.joaosilveira.challengeauthsecurity.dto.*;
import com.joaosilveira.challengeauthsecurity.entities.Role;
import com.joaosilveira.challengeauthsecurity.entities.User;
import com.joaosilveira.challengeauthsecurity.repositories.RoleRepository;
import com.joaosilveira.challengeauthsecurity.repositories.UserRepository;
import com.joaosilveira.challengeauthsecurity.services.exceptions.DatabaseException;
import com.joaosilveira.challengeauthsecurity.services.exceptions.ResourceNotFoundException;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserInsertDTO> result = userRepository.searchUserAndRolesByEmail(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        User user = new User();
        seedUserWithRoles(user, username, result);
        return user;
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
    public UserInsertDTO update(Long id, UserInsertDTO dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyUserDtoToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserInsertDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public UserInsertDTO insert(UserInsertDTO dto) {
        User user = new User();
        copyUserDtoToEntity(dto, user);
        // pega a senha que o usuario digitar e transforma ela em um HASH
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user = userRepository.save(user);
        // devolve o DTO que contém também a senha
        return new UserInsertDTO(user);
    }

    public void deleteById(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Violação de integridade referencial");
        }

    }



    private void copyUserDtoToEntity(UserInsertDTO dto, User entity) {
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

    private void copyRoleDtoToEntity(RoleDTO roleDTO, Role entity) {
        entity.setId(roleDTO.getId());
        entity.setAuthority(roleDTO.getAuthority());
    }

    private void seedUserWithRoles(User user, String username, List<UserInsertDTO> result) {
        user.setEmail(username);

        user.setPassword(result.get(0).getPassword());

        List<RoleDTO> roleDTOS = new ArrayList<>();

        for (UserInsertDTO userDTO : result) {
            roleDTOS.addAll(userDTO.getRoles());
        }

        for (RoleDTO role : roleDTOS) {
            user.getRoles().add(new Role(role.getId(), role.getAuthority()));
        }
    }
}
