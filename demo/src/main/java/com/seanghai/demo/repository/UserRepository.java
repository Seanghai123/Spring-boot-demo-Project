package com.seanghai.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.seanghai.demo.enitity.User;
import org.springframework.stereotype.Repository;

//JpaRepository វាទទួលយក ២ ទី១ Enitity មួយទៀតជា Type  របស់ ID
@Repository
public interface UserRepository extends JpaRepository <User , Long>{

}
