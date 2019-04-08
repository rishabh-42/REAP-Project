package com.example.spring.Service;

import com.example.spring.Entities.User;
import com.example.spring.Entities.UserRole;
import com.example.spring.Entities.UserStarCount;
import com.example.spring.Repositories.UserRepository;
import com.example.spring.Repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserStarCountService userStarCountService;

    public UserRole getRole(int i){

        return  userRoleRepository.findById(i);

    }

    public void updateRoles(String email , String roles[]){

        int newPriority=-1;

        Set<UserRole> rolesToBeAdded = new HashSet<>();
        User user = userRepository.findByEmail(email);
        Set<UserRole> userRoles = user.getRoles();
        boolean exits=false;

        int currentPriority=-1;

        for(UserRole userRole : userRoles){
            if(userRole.getPriority()>currentPriority){
                currentPriority=userRole.getPriority();

            }

        }
//        System.out.println("====== cuurent p"+ currentPriority);

        for(int i=0;i<roles.length;i++){
            System.out.println("for "+roles[i]);
            for(UserRole userRole : userRoles){
                if(userRole.getName().equals(roles[i])){
                    exits=true;
                    rolesToBeAdded.add(userRoleRepository.findByName(roles[i]));
                    break;

                }

            }
            if(!exits){

//                userRoles.add(userRoleRepository.findByName(roles[i]));
                rolesToBeAdded.add(userRoleRepository.findByName(roles[i]));

            }

            exits=false;




        }

        for(UserRole userRole : rolesToBeAdded){
            userRoles.add(userRole);
            if(userRole.getPriority()>newPriority)
            {
                if(userRole.getPriority()!=4){
                    newPriority=userRole.getPriority();
                }

            }

        }

//        System.out.println("============ old roles  " + userRoles);
//        System.out.println("============ New roles  " + rolesToBeAdded);
//
//        System.out.println(currentPriority + "   "+ newPriority);


        user.setRoles(rolesToBeAdded);

        userRepository.save(user);

        UserRole roleWithMaxPriority = userRoleRepository.findByPriority(newPriority);

        UserStarCount userStarCount = userStarCountService.findByUser(user);
        userStarCount.setGoldStarCount(roleWithMaxPriority.getGoldStar());
        userStarCount.setSilverStarCount(roleWithMaxPriority.getSilverStar());
        userStarCount.setBronzeStarCount(roleWithMaxPriority.getBronzeStar());

        userStarCountService.saveStars(userStarCount);





    }
}
