//package com.example.spring.utils;
//
//import com.example.spring.Entities.*;
//import com.example.spring.Service.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.Set;
//
//
//@Component
//public class DataBootstrap {
//
//    @Autowired
//    UserService userService;
//    @Autowired
//    UserRoleService userRoleService;
//    @Autowired
//    UserStarRecievedService userStarRecievedService;
//
//    @Autowired
//    UserStarCountService userStarCountService;
//
//    @Autowired
//    StarService starService;
//
//    Logger logger = LoggerFactory.getLogger(DataBootstrap.class);
//
//    @EventListener(ContextRefreshedEvent.class)
//    public void init(){
//
//        System.out.println(userService.findAllUsers() + "");
//        if(userService.findAllUsers().size()==0)
//        {
//            logger.info("Creating admin user");
//            //Adding Stars
//            Star gold = new Star();
//            gold.setName("Gold");
//            gold.setWeight(30);
//            starService.save(gold);
//            logger.info("Gold Created");
//
//            Star silver = new Star();
//            silver.setName("Silver");
//            silver.setWeight(20);
//            starService.save(silver);
//            logger.info("Silver Created");
//
//            Star bronze = new Star();
//            bronze.setName("Bronze");
//            bronze
//                    .setWeight(10);
//            starService.save(bronze);
//            logger.info("Bronze Created");
//
//            //Adding Roles
//            UserRole user = new UserRole();
//            user.setGoldStar(3);
//            user.setSilverStar(2);
//            user.setBronzeStar(1);
//            user.setName("User");
//            user.setPriority(1);
//            UserRole userRole= userRoleService.save(user);
//
//            UserRole supervisor = new UserRole();
//            supervisor.setGoldStar(6);
//            supervisor.setSilverStar(3);
//            supervisor.setBronzeStar(2);
//            supervisor.setName("Supervisor");
//            supervisor.setPriority(2);
//            userRoleService.save(supervisor);
//
//            UserRole practiceHead = new UserRole();
//            practiceHead.setGoldStar(9);
//            practiceHead.setSilverStar(6);
//            practiceHead.setBronzeStar(3);
//            practiceHead.setPriority(3);
//            practiceHead.setName("PracticeHead");
//            userRoleService.save(practiceHead);
//
//            UserRole admin = new UserRole();
//            admin.setPriority(4);
//            admin.setName("Admin");
//           UserRole userRoleAdmin = userRoleService.save(admin);
//
//            Set<UserRole> defaultRoles = new HashSet<>();
//            defaultRoles.add(userRoleAdmin);
//            defaultRoles.add(userRole);
//
//            //Adding Admin user
//            User adminUser = new User();
//            adminUser.setFirstName("Admin");
//            adminUser.setLastName("Admin");
//            adminUser.setActive(true);
//            adminUser.setRoles(defaultRoles);
//            adminUser.setEmail("admin@reap.com");
//            adminUser.setPhoto("/assets/profileImages/default.png");
//            adminUser.setPassword(BCrypt.hashpw("admin", BCrypt.gensalt(4)));
//            User savedUser = userService.saveUser(adminUser);
//
//            UserStarCount userStarCount = new UserStarCount();
//            userStarCount.setUser(savedUser);
//            userStarCount.setGoldStarCount(userRoleService.getRole("User").getGoldStar());
//            userStarCount.setSilverStarCount(userRoleService.getRole("User").getSilverStar());
//            userStarCount.setBronzeStarCount(userRoleService.getRole("User").getBronzeStar());
//            userStarCountService.saveStars(userStarCount);
//
//            UserStarReceived userStarReceived = new UserStarReceived();
//            userStarReceived.setUser(savedUser);
//            userStarReceived.setGoldStarRecieved(new Integer(0));
//            userStarReceived.setBronzeStarRecieved(new Integer(0));
//            userStarReceived.setSilverStarRecieved(new Integer(0));
//            userStarReceived.setPoints(new Integer(0));
//            userStarRecievedService.save(userStarReceived);
//
//            savedUser.setUserStarCount(userStarCount);
//            savedUser.setUserStarReceived(userStarReceived);
//            userService.update(savedUser);
//
//        }
//        else {
//            logger.info("DB already created");
//        }
//
//
//    }
//}
