package com.code81.library.service.impl;
import com.code81.library.entity.Role;
import com.code81.library.entity.SystemUser;
import com.code81.library.repository.SystemUserRepository;
import com.code81.library.service.InitService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class InitServiceImpl implements InitService {
    private final SystemUserRepository repo;
    private final PasswordEncoder encoder;
    public InitServiceImpl(SystemUserRepository repo, PasswordEncoder encoder){
        this.repo = repo; this.encoder = encoder;
    }

    @Override
    public void initUsers() {
        if(repo.count()==0){
            SystemUser admin = new SystemUser();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            repo.save(admin);

            SystemUser librarian = new SystemUser();
            librarian.setUsername("librarian");
            librarian.setPassword(encoder.encode("librarian123"));
            librarian.setRole(Role.LIBRARIAN);
            repo.save(librarian);

            SystemUser staff = new SystemUser();
            staff.setUsername("staff");
            staff.setPassword(encoder.encode("staff123"));
            staff.setRole(Role.STAFF);
            repo.save(staff);
        }
    }
}