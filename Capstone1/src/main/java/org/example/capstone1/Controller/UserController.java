package org.example.capstone1.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.capstone1.Api.ApiResponse;
import org.example.capstone1.Model.User;
import org.example.capstone1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/get")
    public ResponseEntity getCourses(){
        ArrayList<User> user=userService.getUsers();
        return ResponseEntity.status(200).body(user);
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService. addUsers(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser( @PathVariable int id ,@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if(userService.updateUser(id,user)) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated"));
        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteUser(@PathVariable int id ){

        if(userService.deleteUser(id)){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted"));

        }
        else{
            return ResponseEntity.status(400).body(new ApiResponse("Invalid id"));
        }

    }

    @PutMapping("/buyproduct/{id}/{productid}/{merchantid}/{amount}")
    public ResponseEntity buyProduct(@PathVariable int id ,@PathVariable int productid,@PathVariable int merchantid, @PathVariable int amount){
        if(userService. buyProduct(id,productid,merchantid,amount))  {
            return ResponseEntity.status(200).body(new ApiResponse("Product purchased successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Bad Request"));

    }

    @PutMapping("/giftcard/{id}/{role}/{amount}")
    public ResponseEntity giftCard(@PathVariable int id ,@PathVariable String role , @PathVariable int amount){
        if(userService.giftCard(id,role,amount))  {
            return ResponseEntity.status(200).body(new ApiResponse("Gift card added to balance successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only Admins can add gift cards"));

    }

    @PutMapping("/discount/{id}/{percent}")
    public ResponseEntity discount(@PathVariable int id ,@PathVariable int percent){
        if(userService.discount(id,percent))  {
            return ResponseEntity.status(200).body(new ApiResponse(percent+"% Discount added to price successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Only Admins can add gift cards"));

    }


}
