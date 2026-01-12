package main.app.Backend.controller;

import main.app.Backend.Entities.LinkEntity;
import main.app.Backend.Entities.UserDetailsEntity;
import main.app.Backend.Entities.UserEntity;
import main.app.Backend.Services.LinkService;
import main.app.Backend.Services.UserDetailService;
import main.app.Backend.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private LinkService linkService;

    @PostMapping("/signup")
    public UserDetailsEntity saving(@RequestBody UserDetailsEntity userDetailsEntity){
        UserEntity user=new UserEntity(userDetailsEntity.getName(),userDetailsEntity.getGmail(),userDetailsEntity.getPassword(),"user");
        userService.saveUser(user);
        userDetailsEntity.setType("user");
        return userDetailService.saveUserDetails(userDetailsEntity);
    }

    @GetMapping("/login")
    public Map<String,Object> logins(Authentication authentication){
        Optional<UserDetailsEntity> user=userDetailService.findByGmail(authentication.getName());
        Map<String ,Object> map=new HashMap<>();
        map.put("name",user.get().getName());
        map.put("gmail",user.get().getGmail());
        map.put("gender",user.get().getGender());
        map.put("dob",user.get().getDob());
        map.put("image",user.get().getImage());
        map.put("type",user.get().getType());
        System.out.println(user);
        return map;
    }
    @PutMapping("/update")
    public UserDetailsEntity update(Authentication authentication,@RequestBody UserDetailsEntity userDetailsEntity){
        String gmail=authentication.getName();
        Optional<UserEntity> userEntity=userService.findByGmail(gmail);
        Optional<UserDetailsEntity> user=userDetailService.findByGmail(gmail);
        userEntity.get().setName(userDetailsEntity.getName());

        user.get().setImage(userDetailsEntity.getImage());
        user.get().setDob(userDetailsEntity.getDob());

        user.get().setGender(userDetailsEntity.getGender());
        user.get().setName(userDetailsEntity.getName());
        userService.updateUser(userEntity.get());
        return userDetailService.saveUserDetails(user.get());
    }

    @PostMapping("/savelink")
    public LinkEntity saveLinks(@RequestBody LinkEntity linkEntity){
        return linkService.saveLink(linkEntity);
    }
    @GetMapping("/getlink")
    public List<LinkEntity> fetchLinks(){
        return linkService.findLinks();
    }
    @DeleteMapping("/deletelink/{id}")
    public void  deleteLink(@PathVariable long id){
        linkService.deleteLink(id);
    }


}
