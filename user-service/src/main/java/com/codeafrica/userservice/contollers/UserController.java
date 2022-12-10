package com.codeafrica.userservice.contollers;


import com.codeafrica.userservice.dtos.UserDto;
import com.codeafrica.userservice.models.User;
import com.codeafrica.userservice.payload.ApiResponse;
import com.codeafrica.userservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;


    @PostMapping("/create/user")
    public ResponseEntity<ApiResponse> createUser (@RequestBody UserDto userDto){
        try{
            User user = userService.createUser(userDto);
            return new ResponseEntity<>(new ApiResponse<>( true,"User created successfully", user), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse<>( false, e.getMessage(),null),HttpStatus.CONFLICT);
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<User>getSingleUser(@PathVariable("id") Long id){
        User user = userService.getUser(id);
        return  ResponseEntity.ok(user);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>>getAllUser(){
        List<User>allUser = userService.getAllUsers();
        return ResponseEntity.ok(allUser);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ApiResponse<?>> deleteStudent(@PathVariable("id") Long id){
        userService.DeleteUser(id);
        return new ResponseEntity<>(new ApiResponse<>(true,"User deleted successfully",null),HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public void updateUser(@PathVariable("id") Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false)String address){
                userService.updateUser(id,name,email,address);

    }


}
