package com.example.bbsexercise.controller;

import com.example.bbsexercise.domain.entitiy.Hospital;
import com.example.bbsexercise.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/hospitals")
@RequiredArgsConstructor
@Slf4j
public class HospitalController {

    private final HospitalRepository hospitalRepository;

    @GetMapping("")
    public String list(@RequestParam(required = false) String keyword, Model model, @PageableDefault(size = 10) Pageable pageable) {
        Page<Hospital> hospitals;
        if (keyword == null) {
            hospitals = hospitalRepository.findAll(pageable);
        } else {
            hospitals = hospitalRepository.findByRoadNameAddressContaining(keyword, pageable);
        }
        log.info("keyword = {}, hospitals.size={}",keyword, hospitals.getSize());
        model.addAttribute("keyword", keyword);
        model.addAttribute("hospitals", hospitals);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasPrevious", hospitals.hasPrevious());
        model.addAttribute("hasNext", hospitals.hasNext());
        return "hospitals/list";
    }
}
