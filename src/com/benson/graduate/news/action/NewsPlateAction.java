package com.benson.graduate.news.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.news.model.NewsPlate;
import com.benson.graduate.news.service.NewsPlateService;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 新闻板块Action
 * @author benson
 *
 */
@Controller("newsPlateAction")
@Scope("prototype")
public class NewsPlateAction extends BaseAction implements ModelDriven<NewsPlate>,Preparable{

	private static final long serialVersionUID = -1020270756002782332L;
	
	//需要使用到的业务逻辑组件
	private NewsPlateService newsPlateService;
	@Resource(name="newsPlateService")
	public void setNewsPlateService(NewsPlateService newsPlateService) {
		this.newsPlateService = newsPlateService;
	}
	
	//获取前台easy-ui传过来的参数
	private String rows;//每页显示的记录数    
    private String page;//当前第几页
    private String sort; //根据哪个字段排序
    private String order;	//desc,asc
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}

	//查询条件
	private String plateName;
	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}
	//页面参数
	private String isAdd;
	private String type ;
	private int newsPlateId;
	private int plateSelected;
	private String idList;
	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setNewsPlateId(int newsPlateId) {
		this.newsPlateId = newsPlateId;
	}
	public void setPlateSelected(int plateSelected) {
		this.plateSelected = plateSelected;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}

	//ModelDriven
	private NewsPlate model=new NewsPlate();
	
	/**
	 * 进入新闻板块列表页面
	 */
	public String index(){
		System.out.println("mainPage()");
		return "mainPage";
	}
	
	/**
	 * 获取所有新闻板块信息
	 */
	public void list(){
		int pageNow = Integer.parseInt((page == null || page == "0") ? "1" : page);
		int pageRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("plateName", plateName);
		super.writeJson(newsPlateService.getDataGrid(fieldMap,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 新增或者编辑一级、二级版块
	 * @return
	 */
	public String edit(){
		request.setAttribute("isAdd", isAdd);
		request.setAttribute("type", type);
		if(isAdd.equals("0")){
			request.setAttribute("newsPlateId", newsPlateId);
			request.setAttribute("newsPlate", newsPlateService.findById(newsPlateId));
		}
		request.setAttribute("rootNewsPlateList", newsPlateService.findAllRootNewsPlate());
		return "editNewsPlate";
	}
	
	/**
	 * 新增或者编辑提交
	 */
	public void editSubmit(){
		Json json=new Json();
		boolean result ; 
		if("1".equals(isAdd)){
			//新增
			NewsPlate newsPlate = new NewsPlate();
			newsPlate.setNewsType(1);
			newsPlate.setPlateName(plateName);
			if("2".equals(type)){
				//二级版块
				NewsPlate parent = newsPlateService.findById(plateSelected);
				newsPlate.setNewsPlate(parent);
			}
			result = newsPlateService.insertNewsPlate(newsPlate);
		}else{
			//更新
			Map<String, Object> fieldMap = new HashMap<String, Object>();
			fieldMap.put("plateName", plateName);
			if("2".equals(type)){
				//二级版块
				fieldMap.put("newsPlatePid", plateSelected);
			}
			result = newsPlateService.updateNewsPlate(newsPlateId, fieldMap);
		}
		if(result){
			json.setMsg("操作成功");
		}else{
			json.setMsg("操作失败");
		}
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 删除
	 */
	public void delete(){
		//先判断删除的新闻版块是否有关联版块
		Json json = new Json();
		System.out.println("idList:"+idList);
		boolean flag = newsPlateService.isContext(idList);
		if(!flag){
			json.setSuccess(flag);
			json.setMsg("删除的版块中含有子版块，请检查清楚再删除！");
			super.writeJson(json);
			return ;
		}
		boolean result = newsPlateService.deleteNewsPlates(idList);
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	
	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	}
	
	@Override
	public NewsPlate getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	
}
