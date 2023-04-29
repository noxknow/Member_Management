package com.codingrecipe.member.service;

import com.codingrecipe.member.dto.MemberDTO;
import com.codingrecipe.member.entity.MemberEntity;
import com.codingrecipe.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity); // memberRepository의 save는 jpa에서 자동으로 제공해준다.
        // repository의 save 메서드 호출 (조건 entity 객체를 넘겨줘야 함)
    }

    // entity 객체 하나를 dto 객체 하나로 변환하는 과정
    public MemberDTO login(MemberDTO memberDTO) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함.
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
            (이런식으로만 조회한다면 안좋지만 간단한 예제이기 때문에)
        */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()) {
            // 조회 결과가 있다.(해당 이메일을 가진 회원정보가 있다. {하지만 비밀번호가 맞는지는 아직 모름})
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                // 비밀번호 일치 
                // entity -> dto 객체 변환
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                return null;
            }
        } else {
            // 조회 결과가 없다. (해당 이메일을 가진 회원이 없다.)
            return null;
        }
    }

    // entity가 여러개 담긴 리스트 객체를 dto가 여러개 담긴 리스트 객체로 옮기는 과정
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        // memberEntityList를 memberDTOList에 바로 대입하는건 불가능하기에 하나씩 꺼내서 넣어줘야 한다.
        for(MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
//          MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//          memberDTOList.add(memberDTO); 이 두 줄이 위의 한 줄
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()) {
//          MemberEntity memberEntity = optionalMemberEntity.get();
//          MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//          return memberDTO; 같은 내용
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }

    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }

    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다. 반대로 !byMemberEmail.isEmpty()도 가능.
            return null;
        } else {
            return "ok";
        }
    }
}
