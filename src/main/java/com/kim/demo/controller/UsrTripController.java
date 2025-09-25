package com.kim.demo.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kim.demo.service.KeepTripService;
import com.kim.demo.service.TripBoardService;
import com.kim.demo.service.TripService;
import com.kim.demo.util.Util;
import com.kim.demo.vo.Rq;
import com.kim.demo.vo.Trip;

@Controller
public class UsrTripController {

	private TripService tripService;
	private TripBoardService tripBoardService;
	private KeepTripService keepTripService;
	private Rq rq;

	UsrTripController(TripService tripService, TripBoardService tripBoardService, KeepTripService keepTripService, Rq rq) {
		this.tripService = tripService;
		this.tripBoardService = tripBoardService;
		this.keepTripService = keepTripService;
		this.rq = rq;
	}

	List<Trip> trips;

	@RequestMapping("/usr/trip/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int page) {

		if (page <= 0) {
			return rq.jsReturnOnView("페이지 번호가 올바르지 않습니다");
		}

		int getTripsCnt = tripService.getTripsCnt();
		int itemsInApage = 10;
		int limitStart = (page - 1) * itemsInApage;
		int pagesCnt = (int) Math.ceil(((double) getTripsCnt / itemsInApage));

		List<Trip> trips = tripService.getTrips(limitStart, itemsInApage);

		int pageGroupSize = 10;
		int currentPageGroup = (page - 1) / pageGroupSize;
		int startPage = currentPageGroup * pageGroupSize + 1;
		int endPage = startPage + pageGroupSize - 1;

		// 실제 전체 페이지 수보다 endPage가 더 커지지 않도록 막아주는 보호 코드
		if (endPage > pagesCnt) {
			endPage = pagesCnt;
		}
		

		model.addAttribute("trips", trips);
		model.addAttribute("getTripsCnt", getTripsCnt);
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);
		model.addAttribute("pageGroupSize", pageGroupSize);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "usr/trip/list";
	}

	@RequestMapping("/usr/trip/detail")
	public String detail(Model model, int id) {
		
		Trip trip = tripService.getTripById(id);

		if (trip == null) {
			return Util.jsHistoryBack("존재하지 않는 게시물입니다");
		}

		model.addAttribute("trip", trip);
		model.addAttribute("loginMemberId", rq.getLoginedMemberId());

		return "usr/trip/detail";
	}

	@RequestMapping("/usr/trip/write")
	public String write() {
		return "usr/trip/write";
	}

	@RequestMapping("/usr/trip/doWrite")
	@ResponseBody
	public String doWrite(int boardId, String tripStartTime, String tripEndTime, String province, String city, String placeName,
			int price, String body) {

		if (rq.getLoginedMemberId() == 0) {
			return Util.jsHistoryBack("로그인 이후 가능합니다");
		}

		if (Util.empty(city)) {
			return Util.jsHistoryBack("도시를 입력해주세요");
		}

		if (Util.empty(placeName)) {
			return Util.jsHistoryBack("상호명을 입력해주세요");
		}
		
		if (price > 2147483647) {
			return Util.jsHistoryBack("2,147,483,647원 이하로 작성해주세요");
		}

		//keepTrip 캘린더 만들면 사용하자
//		tripStartTime = Util.convertDateTimeFormat(tripStartTime);
//		tripEndTime = Util.convertDateTimeFormat(tripEndTime);
		
		String provinceCity = (province + city).replaceAll(",", "_");

		tripService.writeTrip(boardId, provinceCity, placeName, price, body, (int) rq.getLoginedMemberId());
		
		int tripId = tripService.getLastInsertId();
		

		return Util.jsReplace(Util.f("%d번 게시물이 생성되었습니다", tripId), "list");
	}

	@RequestMapping("usr/trip/doDelete")
	@ResponseBody
	public String doDelete(int id) {

		Trip trip = tripService.getTripById(id);

		if (trip == null) {
			return Util.jsHistoryBack("존재하지 않는 게시물입니다");
		}

		if (trip.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("해당 게시물에 대한 권한이 없습니다");
		}

		tripService.deleteTrip(id);

		return Util.jsReplace(Util.f("%d번 게시물이 삭제되었습니다", id), "list");
	}

	@RequestMapping("/usr/trip/modify")
	public String modify(Model model, int id) {

		Trip trip = tripService.getTripById(id);

		if (trip == null) {
			return Util.jsHistoryBack("존재하지 않는 게시물입니다");
		}

		if (trip.getMemberId() != rq.getLoginedMemberId()) {
			return Util.jsHistoryBack("해당 게시물에 대한 권한이 없습니다");
		}

		model.addAttribute("trip", trip);

		return "usr/trip/modify";
	}

	@RequestMapping("/usr/trip/doModify")
	@ResponseBody
	public String doModify(int id, String provinceCity, String placeName, int price, String description) {

		tripService.modifyTrip(id, provinceCity, placeName, price, description);

		return Util.jsReplace(Util.f("%d번 게시물을 수정했습니다", id), Util.f("detail?id=%d", id));
	}

	@RequestMapping("/usr/trip/doKeepTripInsert")
	@ResponseBody
	public String doKeepTrip(@RequestParam("tripCheckList") List<Integer> tripIds) {
	    
	    if (tripIds == null) {
	        return Util.jsHistoryBack("선택한 여행지가 없습니다");
	    }
	    
	    int loginedMemberId = rq.getLoginedMemberId();
	    
	    List<Integer> memberKeepTrips = KeepTripService.memberberKeepTrips(loginedMemberId);
	    
	    Set<Integer>  keepSet = new HashSet<>(memberKeepTrips);
	    
        // 중복 찾기
        List<Integer> duplicated = tripIds.stream()
            .filter(keepSet::contains)
            .collect(Collectors.toList());
        
        // 새로운 것 찾기
        List<Integer> newTrips = tripIds.stream()
            .filter(tripId -> !keepSet.contains(tripId))
            .collect(Collectors.toList());
        
        if (newTrips.size() == 0) {
        	return Util.jsHistoryBack("새로운 여행지가 없습니다");
        }
        
	    KeepTripService.doKeepTrip(newTrips, loginedMemberId);
	    
	    return Util.jsReplace("이미 저장된 여행지 번호: " + duplicated, "../keepTrip/list");
	}
	
}
