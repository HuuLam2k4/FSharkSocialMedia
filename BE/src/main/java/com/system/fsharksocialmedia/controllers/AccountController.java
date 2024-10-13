package com.system.fsharksocialmedia.controllers;

import com.system.fsharksocialmedia.dtos.UserDto;
import com.system.fsharksocialmedia.repositories.UserRepository;
import com.system.fsharksocialmedia.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    AccountService accountService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAccounts(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "7") int size,
            @RequestParam(required = false) String search) {
        Page<UserDto> accounts = accountService.getUsers(page, size, search);
        return ResponseEntity.ok(accounts);
    }

//    @PutMapping("/{username}")
//    public ResponseEntity<Void> updateAccountStatus(@PathVariable String username, @RequestBody Map<String, Boolean> status) {
//        accountService.updateAccountStatus(username, status.get("active"));
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{username}")
//    public ResponseEntity<Void> deleteAccount(@PathVariable String username) {
//        if (userRepository.existsById(username)) {
//            accountService.deleteAccount(username);
//            return ResponseEntity.ok().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//
//    }

}

