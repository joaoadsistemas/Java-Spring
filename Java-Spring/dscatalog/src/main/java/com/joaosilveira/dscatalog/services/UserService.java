package com.joaosilveira.dscatalog.services;


import com.joaosilveira.dscatalog.dtos.RoleDTO;
import com.joaosilveira.dscatalog.dtos.UserDTO;
import com.joaosilveira.dscatalog.entities.Category;
import com.joaosilveira.dscatalog.entities.Role;
import com.joaosilveira.dscatalog.entities.User;
import com.joaosilveira.dscatalog.repositories.RoleRepository;
import com.joaosilveira.dscatalog.repositories.UserRepository;
import com.joaosilveira.dscatalog.services.exceptions.DatabaseException;
import com.joaosilveira.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


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
    public UserDTO update(Long id, UserDTO dto) {
        try {
            User entity = userRepository.getReferenceById(id);
            copyUserDtoToEntity(dto, entity);
            entity = userRepository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional
    public UserDTO insert(UserDTO dto) {
        User user = new User();
        copyUserDtoToEntity(dto, user);
        user = userRepository.save(user);
        return new UserDTO(user);
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



    private void copyUserDtoToEntity(UserDTO dto, User entity) {
        entity.setEmail(dto.getEmail());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());

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

}
