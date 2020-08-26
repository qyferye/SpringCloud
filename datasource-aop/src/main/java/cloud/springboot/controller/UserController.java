package cloud.springboot.controller;

import cloud.springboot.dao.entity.CloudUser;
import cloud.springboot.service.CloudUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private CloudUserService cloudUserService;
    @PostMapping(value = "/getUser")
    public CloudUser getUser(@RequestParam("id") Integer id) {
        CloudUser cloudUser = cloudUserService.getById(id);
        return cloudUser;
    }
    @GetMapping(value = "/userList")
    public List<CloudUser> getUser() {
        List<CloudUser> cloudUser = cloudUserService.getAll();
        return cloudUser;
    }
}
