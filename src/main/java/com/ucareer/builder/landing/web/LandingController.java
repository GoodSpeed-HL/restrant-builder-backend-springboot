package com.ucareer.builder.landing.web;


import com.ucareer.builder.core.CoreResponseBody;
import com.ucareer.builder.landing.LandingService;
import com.ucareer.builder.landing.models.Landing;
import com.ucareer.builder.user.User;
import com.ucareer.builder.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LandingController {

    @Autowired
    UserService userService;

    @Autowired
    LandingService landingService;

    @GetMapping("/me/builder")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CoreResponseBody> mineBuilder(@RequestHeader("Authorization") String authHeader) {
        String token = this.getJwtTokenFromHeader(authHeader);
        CoreResponseBody res;
        if (token == "") {
            res = new CoreResponseBody(null, "invalid token", new Exception("invalid token"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }

        User user = userService.getUserByToken(token);
        //todo: way 1: builderService => getBuilderByUser
        //    todo: create builderRepository => findBuilderByUser Builder obj

        // todo: modified
        if (user == null) {
            res = new CoreResponseBody(null, "invalid token", new Exception("invalid token"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        res = new CoreResponseBody(user.getLanding(), "get user by username", null);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }


    @PostMapping("/me/builder")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<CoreResponseBody> editBuilder(@RequestHeader("Authorization") String authHeader, @RequestBody Landing builder) {
        String token = this.getJwtTokenFromHeader(authHeader);
        CoreResponseBody res;
        if (token == "") {
            res = new CoreResponseBody(null, "invalid token", new Exception("invalid token"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }

        User user = userService.getUserByToken(token);

        if (user == null) {
            res = new CoreResponseBody(null, "invalid token", new Exception("invalid token"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }

        Landing savedBuilder = landingService.save(builder, user);

        res = new CoreResponseBody(savedBuilder, "builder data", null);
        return ResponseEntity.status(HttpStatus.OK).body(res);


    }

    @GetMapping("/builder/getMine")
    public ResponseEntity<CoreResponseBody> update(@RequestHeader("Authorization") String authHeader) {
        CoreResponseBody res;
        String token = this.getJwtTokenFromHeader(authHeader);
        if (token == "") {
            res = new CoreResponseBody(null, "invalid token", new Exception("invalid token"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }
        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/builder/save")
    public ResponseEntity<CoreResponseBody> save(@RequestHeader("Authorization") String authHeader, @RequestBody Landing builder) {
        CoreResponseBody res;

        String token = this.getJwtTokenFromHeader(authHeader);
        if (token == "") {
            res = new CoreResponseBody(null, "invalid token", new Exception("invalid token"));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
        }

        User user = userService.getUserByToken(token);
        return ResponseEntity.ok(null);
    }


    private String getJwtTokenFromHeader(String authHeader) {
        return authHeader.replace("Bearer ", "").trim();
    }


}