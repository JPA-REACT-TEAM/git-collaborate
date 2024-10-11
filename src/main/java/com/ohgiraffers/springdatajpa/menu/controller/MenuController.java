package com.ohgiraffers.springdatajpa.menu.controller;

import java.util.List;

import com.ohgiraffers.springdatajpa.common.Pagenation;
import com.ohgiraffers.springdatajpa.common.PagingButtonInfo;
import com.ohgiraffers.springdatajpa.menu.dto.MenuDTO;
import com.ohgiraffers.springdatajpa.menu.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/menu")
public class MenuController {

	private final MenuService menuService;

	// @Autowired를 작성하지 않아도 자동 적용됨을 잊지 말자.
	public MenuController(MenuService menuService) {
		this.menuService = menuService;
	}


//	@GetMapping("/list")
//	public String findMenuList(Model model) {
//
//		List<MenuDTO> menuList = menuService.findMenuList();
//
//		model.addAttribute("menuList", menuList);
//
//		return "menu/list";
//	}
	// menuCode
	// 진기

	// menuList
	// 성균
	@GetMapping("/list")
	public String findMenuList(@PageableDefault Pageable pageable, Model model) {

		/* page -> number, size, sort 파라미터가 Pageable 객체에 담긴다. */
		log.info("pageable : {}", pageable);

		Page<MenuDTO> menuList = menuService.findMenuList(pageable);

		log.info("조회한 내용 목록 : {}", menuList.getContent());
		log.info("총 페이지 수 : {}", menuList.getTotalPages());
		log.info("총 메뉴 수 : {}", menuList.getTotalElements());
		log.info("해당 페이지에 표시 될 요소 수 : {}", menuList.getSize());
		log.info("해당 페이지에 실제 요소 수 : {}", menuList.getNumberOfElements());
		log.info("첫 페이지 여부 : {}", menuList.isFirst());
		log.info("마지막 페이지 여부 : {}", menuList.isLast());
		log.info("정렬 방식 : {}", menuList.getSort());
		log.info("여러 페이지 중 현재 인덱스 : {}", menuList.getNumber());

		PagingButtonInfo paging = Pagenation.getPagingButtonInfo(menuList);

		model.addAttribute("paging", paging);
		model.addAttribute("menuList", menuList);

		return "menu/list";
	}


	// queryMethod
	// 예진
	@GetMapping("/querymethod")
	public void queryMethod(){}

	// menuSearch
	@GetMapping("/search")
	public String findByMenuPrice (@RequestParam Integer menuPrice, Model model) {

		List<MenuDTO> menuList = menuService.findByMenuPrice(menuPrice);

		model.addAttribute("menuList", menuList);
		model.addAttribute("menuPrice", menuPrice);

		return "menu/searchResult";
	}


	// menuRegist
	// 성균

	// menuModify
	// 진기

	// menuDelete
	// 예진

}
