package com.benson.graduate.news.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.base.pagemodel.Json;
import com.benson.graduate.common.enumeration.EnumerationType;
import com.benson.graduate.news.model.NewsNn;
import com.benson.graduate.news.service.NewsNnService;
import com.benson.graduate.news.service.NewsPlateService;
import com.benson.graduate.sys.model.EnumerationValue;
import com.benson.graduate.sys.service.EnumerationValueService;
import com.benson.graduate.utils.StringUtil;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * 新闻Action
 * @author benson
 *
 */
@Controller("newsNnAction")
@Scope("prototype")
public class NewsNnAction extends BaseAction implements ModelDriven<NewsNn>,Preparable{

	private static final long serialVersionUID = -1020270756002782332L;
	
	//需要使用到的业务逻辑组件
	private NewsNnService newsNnService;
	@Resource(name="newsNnService")
	public void setNewsNnService(NewsNnService newsNnService) {
		this.newsNnService = newsNnService;
	}
	private NewsPlateService newsPlateService;
	@Resource(name="newsPlateService")
	public void setNewsPlateService(NewsPlateService newsPlateService) {
		this.newsPlateService = newsPlateService;
	}
	private EnumerationValueService enumerationValueService;
	@Resource(name="enumerationValueService")
	public void setEnumerationValueService(
			EnumerationValueService enumerationValueService) {
		this.enumerationValueService = enumerationValueService;
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
	private String createName;
	private String title;
	private String keyWords;
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	//页面参数
	private String isAdd;
	private int id;
	private String idList;
	private String oldImgName;
	private int plateParentId;
	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	public void setOldImgName(String oldImgName) {
		this.oldImgName = oldImgName;
	}
	public void setPlateParentId(int plateParentId) {
		this.plateParentId = plateParentId;
	}
	
	// 封装上传文件域的属性
	private File upload;
	// 封装上传文件类型的属性
	private String uploadContentType;
	// 封装上传文件名的属性
	private String uploadFileName;
	// 直接在struts.xml文件中配置的属性
	// 定义该Action允许上传的文件类型
	private String allowTypes;
	
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getAllowTypes() {
		return allowTypes;
	}
	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}
	
	//ModelDriven
	private NewsNn model=new NewsNn();
	
	/**
	 * 进入新闻列表页面
	 */
	public String index(){
		return "mainPage";
	}
	
	/**
	 * 获取所有新闻信息
	 */
	public void list(){
		int pageNow = Integer.parseInt((page == null || page == "0") ? "1" : page);
		int pageRows = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		System.out.println("测试数据 ："+createName+","+title+","+keyWords);
		fieldMap.put("createName", createName);
		fieldMap.put("title", title);
		fieldMap.put("keyWords", keyWords);
		super.writeJson(newsNnService.getDataGrid(fieldMap,sort,order,pageNow,pageRows));
	}
	
	/**
	 * 新增或者编辑新闻信息
	 * @return
	 */
	public String edit(){
		
		//获取所有一级版块
		request.setAttribute("plateList", newsPlateService.findAllRootNewsPlate());
		
		//更新
		if(isAdd.equals("0")){
			//根据id获取招聘单位信息
			model=newsNnService.findById(id);
			int platePid = model.getPlatePid();
			if(StringUtil.isNotEmpty(platePid+"")){
				//获取该一级版块下的所有二级版块
				request.setAttribute("plateList", newsPlateService.findChildNewsPlate(platePid));
			}
			//二级版块id
			request.setAttribute("id", model.getId());
			request.setAttribute("platePid", platePid);
			request.setAttribute("type", model.getNewsType());
			request.setAttribute("news", model);
		}
		List<EnumerationValue> newsType = enumerationValueService.findAllEnumerationValuesByName(EnumerationType.NEWS_TYPE);
		request.setAttribute("newsType", newsType);
		request.setAttribute("isAdd", isAdd);
		request.setAttribute("platepList", newsPlateService.findAllRootNewsPlate());
		return "editNewsNn";
	}
	
	
	public void uploadImg(){
		Json json =new Json();
		//上传到服务器指定路径
		String realPath = servletContext.getRealPath("/uploadImg");
		System.out.println("realPath:"+realPath);
		System.out.println("oldImgName:"+oldImgName);
		//生成新的文件名
		//获取文件的后缀名 aa.jpg --> jpg
		String newFileName = UUID.randomUUID().toString()+"." +FilenameUtils.getExtension(getUploadFileName());
		try {
			FileUtils.copyFile(upload, new File(realPath + File.separator + newFileName));
			if(StringUtil.isNotEmpty(oldImgName)){
				//新上传图片，需要删除旧的图
				//服务器指定路径
				realPath += "/"+oldImgName;
				newsNnService.deleteFile(realPath);
			}
			json.setSuccess(true);
			json.setMsg(newFileName);
		} catch (IOException e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("上传失败!");
		}
		super.writeJson(json);
	}
	
	/**
	 * 过滤文件类型
	 * @param types 系统所有允许上传的文件类型
	 * @return 如果上传文件的文件类型允许上传，返回null
	 *		   否则返回error字符串
	 */
	public String filterTypes(String[] types){
		// 获取允许上传的所有文件类型
		String fileType = getUploadContentType();
		for (String type : types){
			System.out.println(type);
			if (type.equals(fileType)){
				return null;
			}
		}
		return "nono";
	}
	
	// 执行输入校验
	public void validateUploadImg(){
		// 将允许上传文件类型的字符串以英文逗号（,）
		// 分解成字符串数组从而判断当前文件类型是否允许上传
		String filterResult = filterTypes(getAllowTypes().split(","));
		// 如果当前文件类型不允许上传
		if (filterResult != null){
			// 添加FieldError
			System.out.println("类型不支持");
			Json json =new Json();
			json.setSuccess(false);
			json.setMsg("上传的文件类型有误，请确定图片类型!");
			super.writeJson(json);
			return ;
		}
	}
	
	public void getPlateList(){
		//获取该一级版块下的所有二级版块
		super.writeJson(newsPlateService.findChildNewsPlate(plateParentId));
	}
	
	/**
	 * 新增或者编辑提交
	 */
	public void editSubmit(){
		Json json=new Json();
		boolean result = false;
		System.out.println(model.getCreateName()+","+model.getCoverImg()+","+model.getKeyWords()+","+plateParentId+","+model.getPlateId()+","+model.getSummary()+","+model.getTitle());
		if("1".equals(isAdd)){
			//添加
			model.setPlatePid(plateParentId);
			//时间
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setCreateTime(sdf.format(date));
			result = newsNnService.insertNewsNn(model);
		}else{
			if(StringUtil.isNotEmpty(oldImgName) && StringUtil.isEmpty(model.getCoverImg())){
				//没有上传图片
				model.setCoverImg(oldImgName);
			}
			result = newsNnService.updateNewsNn(model);
		}
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	/**
	 * 删除
	 */
	public void delete(){
		Json json = new Json();
		boolean result = newsNnService.deleteNewsNns(idList);
		json.setSuccess(result);
		super.writeJson(json);
	}
	
	
	@Override
	public void prepare() throws Exception {
		
	}
	
	@Override
	public NewsNn getModel() {
		return model;
	}
	
	
}
