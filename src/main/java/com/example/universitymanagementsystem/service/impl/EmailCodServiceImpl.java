package com.example.universitymanagementsystem.service.impl;

import com.example.universitymanagementsystem.repository.PersonRepository;
import com.example.universitymanagementsystem.service.EmailCode;
import org.springframework.stereotype.Service;

@Service
public class EmailCodServiceImpl implements EmailCode {
    private final EmailCodeRepository rep;
    private final PersonRepository rep2;


    public EmailCodServiceImpl(EmailCodeRepository rep,PersonRepository perRep) {
        this.rep = rep;
        this.rep2 = perRep;
    }

    @Override
    public void createCode(String pn,String email, String code) {
        rep2.findById(20L);
    }

    @Override
    public void compare(String email, String code) {

    }
}
