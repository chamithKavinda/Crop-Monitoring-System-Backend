package org.example.cropmonitoringsystem.service.impl;

import org.example.cropmonitoringsystem.customObj.UserResponse;
import org.example.cropmonitoringsystem.customObj.impl.UserErrorResponse;
import org.example.cropmonitoringsystem.dao.UserDao;
import org.example.cropmonitoringsystem.dto.impl.UserDTO;
import org.example.cropmonitoringsystem.entity.UserEntity;
import org.example.cropmonitoringsystem.enums.Role;
import org.example.cropmonitoringsystem.exception.DataPersistFailedException;
import org.example.cropmonitoringsystem.exception.UserNotFound;
import org.example.cropmonitoringsystem.service.UserService;
import org.example.cropmonitoringsystem.util.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIMPL implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private Mapping mapping;
    @Override
    public void saveUser(UserDTO userDTO) {
        var userEntity = mapping.convertToUserEntity(userDTO);
        var savedUser = userDao.save(userEntity);
        if (savedUser == null){
            throw new DataPersistFailedException("Cannot Save User");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserEntity> getAllUsers = userDao.findAll();
        return mapping.convertUserToDTOList(getAllUsers);
    }

    @Override
    public UserResponse getSelectedUser(String email) {
        if (userDao.existsById(email)) {
            UserEntity userEntityByEmail = userDao.getReferenceById(email);
            return mapping.convertToUserDTO(userEntityByEmail);
        } else {
            return new UserErrorResponse(0, "User not Found");
        }
    }

    @Override
    public void updateUser(String email, UserDTO incomeUserDTO) {
        UserEntity existingUser = userDao.findById(email)
                .orElseThrow(() -> new UserNotFound("User not found with email: " + email));

        UserEntity updatedUser = new UserEntity();
        updatedUser.setEmail(incomeUserDTO.getEmail() != null ? incomeUserDTO.getEmail() : existingUser.getEmail());
        updatedUser.setPassword(incomeUserDTO.getPassword() != null ? incomeUserDTO.getPassword() : existingUser.getPassword());

        if (incomeUserDTO.getRole() != null) {
            updatedUser.setRole(Role.valueOf(String.valueOf(incomeUserDTO.getRole())));
        } else {
            updatedUser.setRole(existingUser.getRole());
        }

        userDao.delete(existingUser);

        userDao.save(updatedUser);
    }
}
