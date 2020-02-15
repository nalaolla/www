package com.nalaolla.www.service;

import com.nalaolla.www.config.CacheKey;
import com.nalaolla.www.domain.Role;
import com.nalaolla.www.domain.entity.MemberEntity;
import com.nalaolla.www.domain.repository.MemberRepository;
import com.nalaolla.www.dto.MemberDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {

    private MemberRepository memberRepository;

    @Transactional
    public Long joinUser(MemberDto memberDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Boolean isExist =  memberRepository.findByUserid(memberDto.getUserid()).isPresent();

        if (!isExist) {
            return memberRepository.save(memberDto.toEntity()).getSeq();
        } else {
            System.out.println("user already..");
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        Optional<MemberEntity> memberEntityWrapper = memberRepository.findByUserid(userid);

        MemberEntity memberEntity = memberEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        System.out.println(userid);

        if (("admin").equals(userid)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return new User(memberEntity.getUserid(), memberEntity.getPassword(), authorities);
    }

    @Cacheable(value = CacheKey.USER, key = "#seq", unless = "#result == null")
    public MemberEntity loadUserBySeq(Long seq) {
//        MemberEntity memberEntity = memberRepository.findBySeq(seq);
//        MemberDto memberDto = new MemberDto();
//        memberDto.setEmail(memberEntity.getEmail());
//
//        return memberDto;
        return memberRepository.findBySeq(Long.valueOf(seq));
    }


}
