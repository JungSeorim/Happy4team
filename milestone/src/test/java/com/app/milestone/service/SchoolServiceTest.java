package com.app.milestone.service;

import com.app.milestone.domain.SchoolDTO;
import com.app.milestone.domain.Search;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SchoolServiceTest {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private PeopleService peopleService;

    //    보육원 회원가입
    @Test
    public void schoolSignUpTest() {
        SchoolDTO schoolDTO = new SchoolDTO();
        schoolDTO.setUserEmail("cyon8254@gmail.com");
        schoolDTO.setUserPassword("phj@971204");
        schoolDTO.setUserName("박해준");
        schoolDTO.setSchoolName("박해준보육원");
        schoolDTO.setSchoolTeachers(1);
        schoolDTO.setSchoolKids(1);
        schoolDTO.setSchoolBudget(10000);
        schoolDTO.setSchoolBank("하나은행");
        schoolDTO.setSchoolAccount("94290200120288");
        schoolDTO.setUserPhoneNumber("01021208515");
        schoolDTO.setSchoolPhoneNumber("0317578254");

        schoolService.schoolSignUp(schoolDTO);
    }

    //    도움이 필요한 보육원
    @Test
    public void needHelpListTest() {
        schoolService.needHelpList().forEach(o -> log.info("" + o.getDonationCount()));
    }

    //    보육원 정보
    @Test
    public void schoolInfoTest() {
        log.info("" + schoolService.schoolInfo(5L));
    }

    //    보육원 목록
    @Test
    public void schoolListTest() {
        Pageable pageable = PageRequest.of(0, 10);
        Search search = new Search();
        search.setSchoolName("코끼리");
        search.setSchoolAddress(new ArrayList<>());
        search.getSchoolAddress().add("바다");
        search.getSchoolAddress().add("사막");
        schoolService.schoolList(pageable, search).forEach(o -> log.info("adsdafas" + o));
    }

    //    총 보육원 수
    @Test
    public void schoolTotalTest() {
        log.info(schoolService.schoolTotal() + "sadfasdfads");
    }

    //   보육원 하나에 대한 최근 기부받은 내역
    @Test
    public void recentDonationList() {
        schoolService.recentDonationList(105L).forEach(o -> log.info("기부자 : " + o.getUserName() + " 기부금 : " + o.getMoneyCash()));
    }

    //    보육원 하나에 대한 기부금 랭킹
    @Test
    public void moneyDonationRankingForOneSchool() {
        schoolService.moneyDonationRankingForOneSchool(105L).forEach(o -> log.info("기부금 : " + o.getMoneyCash() + " 기부자 : " + o.getUserName()));
    }

}
