package rikkei.accademy.controller;

import rikkei.accademy.config.Config;
import rikkei.accademy.dto.request.SignInDTO;
import rikkei.accademy.dto.request.SinUpDTO;
import rikkei.accademy.dto.response.ResponseMessenger;
import rikkei.accademy.model.role.Role;
import rikkei.accademy.model.role.RoleName;
import rikkei.accademy.model.User;
import rikkei.accademy.service.role.IRoleService;
import rikkei.accademy.service.role.RoleServiceIMPL;
import rikkei.accademy.service.user.IUserService;
import rikkei.accademy.service.user.UserServiceIMPL;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {

    IRoleService roleService = new RoleServiceIMPL();
    IUserService userService = new UserServiceIMPL();

    public List<User> getUserList() {
        return userService.findAll();
    }

    public ResponseMessenger register(SinUpDTO sinUpDTO) {
        if (userService.existedByUserName(sinUpDTO.getUsername())) {
            return new ResponseMessenger("Username_Existed!!!");
        }
        if (userService.existedByMail(sinUpDTO.getEmail())){
            return new ResponseMessenger("Email_Existed!!!");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(new RoleServiceIMPL().findByRoleName(RoleName.PLAYER));
       User user = new User(
               sinUpDTO.getId(),
               sinUpDTO.getName(),
               sinUpDTO.getUsername(),
               sinUpDTO.getEmail(),
               sinUpDTO.getPassword(),
               roles
       );
       userService.save(user);
       getUserList();
       return new ResponseMessenger("Create success");

    }
    public ResponseMessenger login(SignInDTO sinUpDTO){
        if (userService.checkLogin(sinUpDTO.getUsername(),sinUpDTO.getPassword())){
            User user = userService.findByUserName(sinUpDTO.getUsername());

            List<User> userLogin = new ArrayList<>();
            userLogin.add(user);
            new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,userLogin);
            return new ResponseMessenger("Login-Success");
        }else {
            return new ResponseMessenger("Login-Failed!!!");
        }
    }
    public User grtCurrenUser(){
        return userService.getCurrenUser();
    }

    public void logOut(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(Config.PATH_USER_PRINCIPAL);
            fileOutputStream.write(("").getBytes());
            fileOutputStream.close();
        } catch (IOException i){
            i.printStackTrace();
        }
    }
    public void changeStatus(int id){
        userService.changeStatus(id);
    }
    public User findById(int id){
        return userService.findById(id);
    }
    public List<User> findByRoleName(RoleName... roleNames){
        return userService.findByRole(roleNames);
    }
    public boolean existByEmail(String email){
        return userService.existedByMail(email);
    }
    public void deleteUserById(int id){
        userService.remove(id);
    }


    public void changeRole (User user){
        Set<Role> roleSet =new HashSet<>();
        roleSet.add(roleService.findByRoleName(RoleName.COACH));
        user.setRoles(roleSet);
        userService.save(user);
    }
    public User detailUser(int id){
        return userService.findById(id);
    }
    public void upDateProFile(User user){
        userService.changeProFile(user);
    }


}
