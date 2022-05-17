package com.example.transcriboot.controller;

import com.example.transcriboot.exception.ResourceNotFoundException;
import com.example.transcriboot.model.Login;
import com.example.transcriboot.model.Message;
import com.example.transcriboot.model.User;
import com.example.transcriboot.repository.MessageRepository;
import com.example.transcriboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private MessageRepository messsageRepository;


    // methodes for user

    // methode return all users
    @GetMapping("/users/getList")
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    //methode return users by id
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    // methode create user
    @PostMapping("/users/create")
    public User createUser(@RequestBody User user) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& " + user);
        return repository.save(user);
    }

    //methode update user
    @PutMapping("/users/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                           @RequestBody User userDetails) throws ResourceNotFoundException {


        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
        System.out.println("+++++++++++++++++++++++   "+userDetails.getEmail());

        //email
        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        } else {
            user.setEmail(user.getEmail());
        }
        //firstname
        if (userDetails.getFirstName() != null) {
            user.setFirstName(userDetails.getFirstName());
        } else {
            user.setFirstName(user.getFirstName());
        }
        //lastname
        if (userDetails.getLastName() != null) {
            user.setLastName(userDetails.getLastName());
        } else {
            user.setLastName(user.getLastName());
        }
        //phone_number
        if (userDetails.getPhoneNumber() != null) {
            user.setPhoneNumber(userDetails.getPhoneNumber());
        } else {
            user.setPhoneNumber(user.getPhoneNumber());
        }
        // id
        if (userDetails.getId() != null) {
            user.setId(userDetails.getId());
        } else {
            user.setId(user.getId());
        }

        /* user.setPhoneNumber(userDetails.getPhoneNumber());*/
        /* user.setLastName(userDetails.getLastName());*/
        /* user.setFirstName(userDetails.getFirstName());*/
        /* user.setId(userDetails.getId());*/
        System.out.println(user);
        final User updatedUser = repository.save(user);
        System.out.println("---------------------- " + ResponseEntity.ok(updatedUser));
        return ResponseEntity.ok(updatedUser);
    }

    //methode delete user
    @DeleteMapping("/users/delete/{id}")
    public void deleteUSer(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        Long Id = null;
        User user = repository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + userId));
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& " + user);
        repository.delete(user);

    }

    // methodes signup & signin

    //methode login
   /* @PostMapping("/users/login")
    public User login(@RequestBody User user) {
        System.out.println(user);
        User loggedUser = this.repository.findByEmail(user.getEmail());

        System.out.println("7777777777777777777777777777777777 " + loggedUser);
        if (loggedUser != null && (loggedUser.getPassword().equals(user.getPassword()))) {
            return loggedUser;
        } else
            return null;
    }*/
    //methode login jdida
   /* @PostMapping("/users/login")
    public User login(@RequestBody String email ,String  password) {
       //System.out.println(email + password);
        System.out.println("666666666666666666666666"+email);
        System.out.println("44444444444444444444"+ password);
        User loggedUser = this.repository.findByEmail(email);

        System.out.println("7777777777777777777777777777777777 " + loggedUser);
        if (loggedUser != null && (loggedUser.getPassword().equals(password))) {
            return loggedUser;
        } else
            return null;
    }*/

    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody Login login) throws Exception {
         User user =  repository.findByEmailAndPassword(login.getEmail(), login.getPassword());

        if(user == null){
              return new ResponseEntity("No such user", HttpStatus.NO_CONTENT);
         }
         else{
            return new ResponseEntity( user , HttpStatus.OK);
         }
    }//NOT_FOUND


    //methode signup
    @PostMapping("/users/signup")
    public User signup(@RequestBody User user) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& " + user);
        return repository.save(user);
    }
   /* @PostMapping("/users/signup")
    public User signup(@RequestBody User user) {
        try {
            User u = new User(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
            this.repository.save(u);
            System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& " + user);
            return u;
        } catch (Exception E) {
            return null;
        }

    }*/

    //contact part

    // methode create message
    @PostMapping("/messages/add")
    public Message addmessage(@RequestBody Message message ) {
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "+message);
        return  messsageRepository.save( message);
    }

    //methode consulter tous message

    @GetMapping("/messages/consulterList")
    public List<Message> getAllMessages(){
        return messsageRepository.findAll();
    }


    // methode consulter les message by id
    @GetMapping ( "/messages/{id}")
    public ResponseEntity<Message> getMessageById(@PathVariable(value ="id") Long messageId)
            throws ResourceNotFoundException {
        Message message = messsageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found for this id :: " + messageId));
        return ResponseEntity.ok().body(message);
    }

    // methode delete message

    @DeleteMapping("/messages/delete/{id}")
    public  void deleteMessage(@PathVariable(value = "id") Long messageId)
            throws ResourceNotFoundException {
        Long Id = null;
        Message message = messsageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found for this id :: " + messageId));
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&& "+message);
        messsageRepository.delete(message);

    }

}
