package com.benson.graduate.front.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Pager;
import com.benson.graduate.base.pagemodel.SessionInfo;
import com.benson.graduate.common.Constant;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.company.service.RecruitmentInfoService;
import com.benson.graduate.company.service.RecruitmentUnitService;
import com.benson.graduate.news.model.NewsNn;
import com.benson.graduate.news.service.NewsNnService;
import com.benson.graduate.news.service.NewsPlateService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.benson.graduate.utils.CastUtil;

@Controller("homeAction")
@Scope("prototype")
public class HomeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private RecruitmentInfoService recruitmentInfoService;
	@Resource(name="recruitmentInfoService")
	public void setRecruitmentInfoService(RecruitmentInfoService recruitmentInfoService) {
		this.recruitmentInfoService = recruitmentInfoService;
	}
	private RecruitmentUnitService recruitmentUnitService;
	@Resource(name="recruitmentUnitService")
	public void setRecruitmentUnitService(
			RecruitmentUnitService recruitmentUnitService) {
		this.recruitmentUnitService = recruitmentUnitService;
	}
	private NewsPlateService newsPlateService;
	@Resource(name="newsPlateService")
	public void setNewsPlateService(NewsPlateService newsPlateService) {
		this.newsPlateService = newsPlateService;
	}
	private NewsNnService newsNnService;
	@Resource(name="newsNnService")
	public void setNewsNnService(NewsNnService newsNnService) {
		this.newsNnService = newsNnService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
	}
	
	private String plateId ;
	private String pageNumber;
	private String pageSize;
	private String sort;
	private String whatType;
	private String newsNnId;
	public void setPlateId(String plateId) {
		this.plateId = plateId;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public void setWhatType(String whatType) {
		this.whatType = whatType;
	}
	public void setNewsNnId(String newsNnId) {
		this.newsNnId = newsNnId;
	}
	
	//前台首页
	public String toHomeIndex(){
		HttpServletRequest request = ServletActionContext.getRequest();
		//进入前台首页
		SessionInfo sessionInfo = (SessionInfo) ServletActionContext.getRequest().getSession().getAttribute("sessionInfo");
		if(sessionInfo != null){
			request.setAttribute("sessionInfo", sessionInfo);
		}
		//其他新闻类
		Map<Integer,List<NewsNn>> nnMap = new HashMap<Integer, List<NewsNn>>();
		Map<Integer,String> enNameMap = new HashMap<Integer, String>();
		List<EnumerationValue> newsTypeList = enumerationValueService.findAllEnumerationValuesByName(EnumerationType.NEWS_TYPE);
		for(EnumerationValue enVal : newsTypeList){
			List<NewsNn> nnList = newsNnService.findByNewsPlateId(enVal.getId());
			nnMap.put(enVal.getId(), nnList);
			enNameMap.put(enVal.getId(), enVal.getName());
		}
		request.setAttribute("newsPlateList", newsPlateService.findAllRootNewsPlate());
		request.setAttribute("infosList", recruitmentInfoService.getAllRecruitmentInfos(0, 4));
		request.setAttribute("unitsList", recruitmentUnitService.findAllRecruitmentUnits());
		request.setAttribute("nnMap", nnMap);
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("enNameMap", enNameMap);
		return "homeIndex";
	}
	
	

	//登录界面
	public String doNotNeedSession_toLogin(){
		return "login";
	}
	
	public String no_nnlist(){
		System.out.println("test: "+plateId+","+pageNumber+","+pageSize+","+sort+","+whatType);
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("sort", sort);
		Pager newsNnPager = null;
		if(Constant.PLATES_TYPE.equals(whatType)){
			fieldMap.put("platePid", plateId);
			newsNnPager = newsNnService.findNewsNnPager(CastUtil.castInt(pageNumber), CastUtil.castInt(pageSize), fieldMap);
		}else if(Constant.NEWS_TYPE.equals(whatType)){
			fieldMap.put("newsType", plateId);
			newsNnPager = newsNnService.findNewsNnPager(CastUtil.castInt(pageNumber), CastUtil.castInt(pageSize), fieldMap);
		}else{
			newsNnPager = new Pager();
		}
		request.setAttribute("newsNnPager", newsNnPager);
		return "nnList";
	}
	
	public String no_nnIndex(){
		List<EnumerationValue> newsTypeList = enumerationValueService.findAllEnumerationValuesByName(EnumerationType.NEWS_TYPE);
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("eachList", newsPlateService.findAllRootNewsPlate());
		request.setAttribute("plateId", plateId);
		request.setAttribute("whatType", "platesType");
		return "nnIndex";
	}
	
	public String no_nnTypeIndex(){
		List<EnumerationValue> newsTypeList = enumerationValueService.findAllEnumerationValuesByName(EnumerationType.NEWS_TYPE);
		request.setAttribute("eachList", newsTypeList);
		request.setAttribute("newsTypeList", newsTypeList);
		request.setAttribute("plateId", plateId);
		request.setAttribute("whatType", "newsType");
		return "nnIndex";
	}
	
	public String no_nnContent(){
		NewsNn newsNn = newsNnService.findById(CastUtil.castInt(newsNnId));
		if(newsNn == null){
			request.setAttribute("msg", "");
			return "noRescource";
		}else{
			List<EnumerationValue> newsTypeList = enumerationValueService.findAllEnumerationValuesByName(EnumerationType.NEWS_TYPE);
			newsNn.setViewCount(newsNn.getViewCount()+1);
			newsNnService.updateNewsNn(newsNn);
			request.setAttribute("newsTypeList", newsTypeList);
			request.setAttribute("newsNn", newsNn);
			return "nn_content";
		}
	}
	
	//后台首页
	public String toBackstage(){
		return "backstage";
	}
	
}
