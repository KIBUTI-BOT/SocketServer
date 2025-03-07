package com.kibuti.socketserver.GlobeAuthentication.Repository;


import com.kibuti.socketserver.GlobeAuthentication.Entity.GlobeUserEntity;
import com.kibuti.socketserver.GlobeAuthentication.Entity.UserOTP;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserOTPRepository extends JpaRepository<UserOTP, Long> {
    UserOTP findUserOTPByUser(GlobeUserEntity userManger);
}
