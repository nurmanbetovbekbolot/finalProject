//package kg.itacademy.gsg.controllers;
//
//import kg.itacademy.gsg.models.AuthenticationRequest;
//import kg.itacademy.gsg.models.AuthenticationResponse;
//import kg.itacademy.gsg.services.impls.MyUserDetailsService;
//import kg.itacademy.gsg.utils.JwtUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class GreetingController {
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    MyUserDetailsService userDetailsService;
//
//    @Autowired
//    JwtUtil jwtTokenUtil;
//
////    @GetMapping("/wordsList")
////    public String getAllWords() {
////        model.addAttribute("allWords",wordService.findAll());
////        return "authorization/login";
////    }
//
//    @RequestMapping("/hello")
//    public String hello() {
//        return "Hello world";
//    }
//
//    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//            );
//        } catch (BadCredentialsException e) {
//            throw new Exception("Incorrect username or password", e);
//        }
//        final UserDetails userDetails = userDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());
//        final String jwt = jwtTokenUtil.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));
//    }
//}
