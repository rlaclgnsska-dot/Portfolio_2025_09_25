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
import com.kim.demo.util.Util;
import com.kim.demo.vo.Rq;
import com.kim.demo.vo.Trip;

@Controller
public class UsrKeepTripController {

	private KeepTripService keepTripService;
	private Rq rq;

	UsrKeepTripController(KeepTripService keepTripService, Rq rq) {
		this.keepTripService = keepTripService;
		this.rq = rq;
	}

	List<Trip> trips;

	@RequestMapping("/usr/keepTrip/list")
	public String list(Model model, @RequestParam(defaultValue = "1") int page) {

		if (page <= 0) {
			return rq.jsReturnOnView("페이지 번호가 올바르지 않습니다");
		}
		
		int loginedMemberId = rq.getLoginedMemberId();
		
		int getKeepTripsCnt = keepTripService.getTripsCnt(loginedMemberId);
		int itemsInApage = 10;
		int limitStart = (page - 1) * itemsInApage;
		int pagesCnt = (int) Math.ceil(((double) getKeepTripsCnt / itemsInApage));
		
		if (getKeepTripsCnt == 0) {
			return rq.jsReturnOnView("getTripsCnt 이상");
		}

		List<Trip> KeepTrips = keepTripService.getKeepTrips(limitStart, itemsInApage, loginedMemberId);

		
		if (KeepTrips == null) {
			return rq.jsReturnOnView("KeepTrips 이상");
		}
		
		int pageGroupSize = 10;
		int currentPageGroup = (page - 1) / pageGroupSize;
		int startPage = currentPageGroup * pageGroupSize + 1;
		int endPage = startPage + pageGroupSize - 1;

		// 실제 전체 페이지 수보다 endPage가 더 커지지 않도록 막아주는 보호 코드
		if (endPage > pagesCnt) {
			endPage = pagesCnt;
		}

		model.addAttribute("keepTrips", KeepTrips);
		model.addAttribute("getTripsCnt", getKeepTripsCnt);
		model.addAttribute("pagesCnt", pagesCnt);
		model.addAttribute("page", page);
		model.addAttribute("pageGroupSize", pageGroupSize);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);

		return "usr/keepTrip/list";
	}
	
	@RequestMapping("usr/keepTrip/doKeepTripDelete")
	@ResponseBody
	public String doKeepTripDelete(@RequestParam("tripCheckList") List<Integer> tripIds) {
		
	    if (tripIds == null) {
	        return Util.jsHistoryBack("선택한 여행지가 없습니다");
	    }
		
	    int loginedMemberId = rq.getLoginedMemberId();
	    
	    List<Integer> memberKeepTrips = KeepTripService.memberberKeepTrips(loginedMemberId);
	    
	    Set<Integer>  keepSet = new HashSet<>(memberKeepTrips);
	    
        List<Integer> duplicated = tripIds.stream()
            .filter(keepSet::contains)
            .collect(Collectors.toList());
        
        if (duplicated.size() == 0) {
        	return Util.jsHistoryBack("삭제할 여행지가 없습니다");
        }
		
	    KeepTripService.doKeepTripDelete(duplicated, loginedMemberId);
	    
		return Util.jsReplace(Util.f("선택한 여행지를 삭제했습니다", loginedMemberId), "usr/keepTrip/list");
	}
	
}
