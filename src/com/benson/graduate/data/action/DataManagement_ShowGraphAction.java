package com.benson.graduate.data.action;

import java.awt.Font;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.benson.graduate.base.action.BaseAction;
import com.benson.graduate.stu.model.Clazz;
import com.benson.graduate.stu.model.Department;
import com.benson.graduate.stu.model.MajorField;
import com.benson.graduate.stu.model.Student;
import com.benson.graduate.stu.service.ClazzService;
import com.benson.graduate.stu.service.DepartmentService;
import com.benson.graduate.stu.service.GraduateInfoService;
import com.benson.graduate.stu.service.MajorFieldService;
import com.benson.graduate.stu.service.StudentService;

/**
 * 显示图表的Action
 * @author benson
 */
@Controller("showGraphAction")
@Scope("prototype")
public class DataManagement_ShowGraphAction extends BaseAction {
	
	private static final long serialVersionUID = 4472573902174263187L;
	private DepartmentService departmentService;
	@Resource(name="departmentService")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	private StudentService studentService;
	@Resource(name="studentService")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	private MajorFieldService majorFieldService;
	@Resource(name="majorFieldService")
	public void setMajorFieldService(MajorFieldService majorFieldService) {
		this.majorFieldService = majorFieldService;
	}
	private ClazzService clazzService;
	@Resource(name="clazzService")
	public void setClazzService(ClazzService clazzService) {
		this.clazzService = clazzService;
	}
	private GraduateInfoService graduateInfoService;
	@Resource(name="graduateInfoService")
	public void setGraduateInfoService(GraduateInfoService graduateInfoService) {
		this.graduateInfoService = graduateInfoService;
	}
	
	//jfreechart需要的变量
	private JFreeChart chart;
	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	//接收从showGraph.jsp传送过来的参数
	private String graTime;
	private Integer departmentId;
	private Integer majorId;
	private Integer graphType;
	private String type;
	public String getGraTime() {
		return graTime;
	}
	public void setGraTime(String graTime) {
		this.graTime = graTime;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getMajorId() {
		return majorId;
	}
	public void setMajorId(Integer majorId) {
		this.majorId = majorId;
	}
	public Integer getGraphType() {
		return graphType;
	}
	public void setGraphType(Integer graphType) {
		this.graphType = graphType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 进入显示图表的页面
	 * @return
	 */
	public String toShowGraphPage(){
		//查询所有专业
		List<Department> department =departmentService.findAllDepartments();
		request.setAttribute("department",department);
		return "showGraphPage";
	}
	
	/**
	 * 创建毕业率图表
	 * @return
	 */
	public String showGraph(){
		return "success";
	}
	
	public JFreeChart getChart(){
		//对日期处理
		graTime=graTime.substring(0,graTime.indexOf("-"));
		System.out.println("test    :"+graTime+"    "+departmentId+"   "+majorId+"  "+graphType+"    是毕业率图还是就业率图:  "+type);
		
		//创建数据集
		Map<String,Float> map=new HashMap<String, Float>();
		//图的标题
		String title="";
		//图的纵坐标和横坐标
		String xLabel="",yLabel="";
		
		if(departmentId==0){
				//证明要查全部学院的毕业率和就业率
				List<Department> departments=departmentService.findAllDepartments();
				//根据学院id查找所有学生
				if(departments!=null&&departments.size()>0){
					for(Department dept:departments){
						List<Student> students=studentService.findStudentsByDeparmentId(dept.getId(),graTime);
						if(type.equals("1")){
							//查询全部学院的毕业率
							//人数
							int graCount=0;
							int notGraCount=0;
							if(students!=null&&students.size()>0){
								for(Student stu:students){
									if(stu.getSchoolStatus()!=5001&&stu.getSchoolStatus()!=5007){
										notGraCount++;
									}else if(stu.getSchoolStatus()==5007){
										graCount++;
									}
								}
							}
							map.put(dept.getName(), (float)graCount/(notGraCount+graCount));
							title=graTime+"年各学院毕业率图";
							yLabel="毕业率";
						}else{
							//查询全部学院的就业率(type="2")
							//毕业生就业人数与未就业人数
							int notWorkCount=0;
							int workCount=0;
							if(students!=null&&students.size()>0){
								for(Student stu:students){
									if(stu.getSchoolStatus()==5007){
										//就业率计算前提是已经毕业
										//从就业信息中是否找到该学生的就业信息，而且前提是已经通过学校审核
										if(graduateInfoService.findGraduateInfoByStuId(stu.getId())){
											//找到学生的就业信息
											workCount++;
										}else{
											notWorkCount++;
										}
									}
								}
							}
							map.put(dept.getName(), (float)workCount/(notWorkCount+workCount));
							title=graTime+"年各学院就业率图";
							yLabel="就业率";
						}
					}
				}
				xLabel="学院";
		}else{
			if(majorId==0){
				//查询某学院所有专业的毕业率和就业率（已经选了学院）
				//根据学院id查找所有的专业
				List<MajorField> majors =majorFieldService.findAllMajorsByDepartmentId(departmentId);
				//根据学院id查找学院
				Department department=departmentService.findDepartmentById(departmentId);
				if(majors!=null&&majors.size()>0){
					for(MajorField major:majors){
						//根据专业id查找所有学生
						List<Student> students=studentService.findStudentsByMajorId(major.getId(), graTime);
						if(type.equals("1")){
							//查找该学院全部专业的就业率
							//人数
							int graCount=0;
							int notGraCount=0;
							if(students!=null&&students.size()>0){
								for(Student stu:students){
									if(stu.getSchoolStatus()!=5001&&stu.getSchoolStatus()!=5007){
										notGraCount++;
									}else if(stu.getSchoolStatus()==5007){
										graCount++;
									}
								}
							}
							map.put(major.getName(), (float)graCount/(notGraCount+graCount));
							title=graTime+"年"+department.getName()+"学院各专业毕业率图";
							yLabel="毕业率";
						}else{
							//查询该学院全部专业的就业率(type="2")
							//毕业生就业人数与未就业人数
							int notWorkCount=0;
							int workCount=0;
							if(students!=null&&students.size()>0){
								for(Student stu:students){
									if(stu.getSchoolStatus()==5007){
										//就业率计算前提是已经毕业
										//从就业信息中是否找到该学生的就业信息，而且前提是已经通过学校审核
										if(graduateInfoService.findGraduateInfoByStuId(stu.getId())){
											//找到学生的就业信息
											workCount++;
										}else{
											notWorkCount++;
										}
									}
								}
							}
							map.put(major.getName(), (float)workCount/(notWorkCount+workCount));
							title=graTime+"年"+department.getName()+"学院各专业就业率图";
							yLabel="就业率";
						}
					}
				}
				xLabel="专业";
			}else{
				//查询某个专业所有班的毕业率和就业率（已经选了专业）
				//根据专业id查询所有班别
				List<Clazz> clazzes=clazzService.findAllClazzByMajorId(majorId);
				//根据专业id获得专业
				MajorField major=majorFieldService.findMajorById(majorId);
				if(clazzes!=null&&clazzes.size()>0){
					for(Clazz clazz:clazzes){
						//根据班级id查找所有毕业学生
						List<Student> students= studentService.findStudentsByClazzId(clazz.getId(), graTime);
						if(type.equals("1")){
							//查询某个专业所有班的毕业率
							//人数
							int graCount=0;
							int notGraCount=0;
							if(students!=null&&students.size()>0){
								for(Student stu:students){
									if(stu.getSchoolStatus()!=5001&&stu.getSchoolStatus()!=5007){
										notGraCount++;
									}else if(stu.getSchoolStatus()==5007){
										graCount++;
									}
								}
							}
							map.put(clazz.getName(), (float)graCount/(notGraCount+graCount));
							title=graTime+"年"+major.getName()+"专业各班毕业率图";
							yLabel="毕业率";
						}else{
							//查询某个专业所有班的就业率
							//毕业生就业人数与未就业人数
							int notWorkCount=0;
							int workCount=0;
							if(students!=null&&students.size()>0){
								for(Student stu:students){
									if(stu.getSchoolStatus()==5007){
										//就业率计算前提是已经毕业
										//从就业信息中是否找到该学生的就业信息，而且前提是已经通过学校审核
										if(graduateInfoService.findGraduateInfoByStuId(stu.getId())){
											//找到学生的就业信息
											workCount++;
										}else{
											notWorkCount++;
										}
									}
								}
							}
							map.put(major.getName(), (float)workCount/(notWorkCount+workCount));
							title=graTime+"年"+major.getName()+"专业各班就业率图";
							yLabel="就业率";
						}
					}
				}
				xLabel="班别";
			}
		}
		
		//饼图
		DefaultPieDataset pieDatas=new DefaultPieDataset();
		//折线图
		DefaultCategoryDataset datas=new DefaultCategoryDataset();
		if(graphType==1){
			pieDatas=createPieDataset(map);
			//根据数据集创建图表
			// 创建PieChart对象(标题，数据集，是否显示图例，是否生成工具提示，是否生成URL链接)
		    chart = ChartFactory.createPieChart3D(title, pieDatas,
		            true, true, true);
			//设置图表样式
		    setPieStyle(chart);
		}else if(graphType==2){
			datas=createBarDataSet(map);
			//根据数据集创建图表
			//创建一个柱状图(图表标题,X轴显示标签,Y轴显示标签,数据集,图表方向(水平or垂直),是否显示图例[对于简单图应为false],是否生成工具,是否生成url链接)
		    chart = ChartFactory.createBarChart3D(title,xLabel, yLabel, datas, PlotOrientation.VERTICAL, false, false, true);
		    setBarStyle(chart);
		}else if(graphType==3){
			datas=createLineDataset(map);
			//根据数据集创建折线图
			// 定义图表对象(折线图名称,横坐标名称,纵坐标名称,数据, 水平显示图像)
		    chart = ChartFactory.createLineChart(title,xLabel,yLabel,datas,PlotOrientation.VERTICAL,true,true,true);
		    setLineStyle(chart);
		}
		
		return chart;
	}

	/**
	 * 创建饼图数据集
	 */
	public DefaultPieDataset createPieDataset(Map<String,Float> map){
		//为饼图添加数据
		DefaultPieDataset data=new DefaultPieDataset();
		Set<String> set=map.keySet();
		for(Iterator<String> iterator=set.iterator();iterator.hasNext();){
			String key=iterator.next();
			data.setValue(key, map.get(key));
		}
		return data; 
	}
	
	/**
	 * 创建柱状图数据
	 * @param list
	 * @return
	 */
	public DefaultCategoryDataset createBarDataSet(Map<String,Float> map){
	    DefaultCategoryDataset barDataSet = new DefaultCategoryDataset();
	    Set<String> set=map.keySet();
	    for(Iterator<String> itor=set.iterator();itor.hasNext();){
	        String key=itor.next();
	        barDataSet.addValue(map.get(key), "", key);
	    }
	    return barDataSet;
	}
	
	/**
	 * 生成折线图数据
	 * @return
	 */
	public DefaultCategoryDataset createLineDataset(Map<String,Float> map) {
		DefaultCategoryDataset lineDataSet = new DefaultCategoryDataset();
		//曲线名称
	    String title="毕业率折线";
	    //横轴名称（列名称）
	    Set<String> set=map.keySet();
	    for(Iterator<String> iterator=set.iterator();iterator.hasNext();){
			String key=iterator.next();
			lineDataSet.addValue(map.get(key), title,key);
		}
	    return lineDataSet;
	}	
	
	
	/**
	 * 设置饼状图样式
	 * @param chart
	 */
	public void setPieStyle(JFreeChart chart){
		Font titleFont=new Font("宋体",Font.BOLD,20);
		//设置标题字体
		chart.getTitle().setFont(titleFont);
		//设置提示条字体
		chart.getLegend().setItemFont(new Font("宋体",Font.ITALIC,15));
		//得到绘图区
		PiePlot plot=(PiePlot) chart.getPlot();
		plot.setLabelFont(new Font("宋体",Font.PLAIN,15));
//		//设置分离效果
//		plot.setExplodePercent("IBM", 0.3f);
		//3D图做前景透明效果
		plot.setForegroundAlpha(0.7f);
		plot.setNoDataMessage("没有相应的数据显示"); 
		//设置标签(就是每一块显示的内容，占得比例等) {0}--前面设置的名称 {1}--数值 {2}--百分比（每一份所占的百分比）{3}--总合
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}(毕业率:{1},所占比例:{2})"));
	}
	
	/**
	 * 设置柱状图样式
	 * @param chart
	 */
	public void setBarStyle(JFreeChart chart){
	    CategoryPlot plot=chart.getCategoryPlot();
	    //设置标题字体样式
	    chart.getTitle().setFont(new Font("黑体", Font.ITALIC,22));
	    //取得横轴和设置横轴样式
	    CategoryAxis categoryAxis = plot.getDomainAxis();
	    categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 14));
	    //横轴分类标签
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
	    categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 14));
	    // 横轴 lable 的位置 横轴上的 Lable 45度倾斜 DOWN_45
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
	    
	    //取得纵轴和设置纵轴样式
	    NumberAxis numberAxis = (NumberAxis) plot.getRangeAxis();
	    numberAxis.setLabelFont(new Font("宋体", Font.BOLD, 22));
	     
	    //显示每个柱的数值，并修改该数值的字体属性   
	    BarRenderer3D renderer = new BarRenderer3D();    
	    renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());  
	    renderer.setBaseItemLabelsVisible(true);  
	    //默认的数字显示在柱子中，通过如下两句可调整数字的显示   
	    //注意：此句很关键，若无此句，那数字的显示会被覆盖，给人数字没有显示出来的问题 ，将数字显示在柱状图上面
	    renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(  
	            ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));  
	    renderer.setItemLabelAnchorOffset(10D);  
	     //设置每个地区所包含的平行柱的之间距离   
	     //renderer.setItemMargin(0.3);   
	    plot.setRenderer(renderer);  
	}
	
	/**
	 * 设置折线图样式
	 * 
	 * @param chart
	 */
	public void setLineStyle(JFreeChart chart){
	    CategoryPlot plot = chart.getCategoryPlot();
	    //无数据时显示内容
	    plot.setNoDataMessage("无对应的数据，请重新查询");
	    
	    //设置标题字体样式
	    chart.getTitle().setFont(new Font("黑体", Font.ITALIC,22));
	    //取得横轴和设置横轴样式
	    CategoryAxis categoryAxis = plot.getDomainAxis();
	    categoryAxis.setLabelFont(new Font("宋体", Font.BOLD, 14));
	    //横轴分类标签
	    categoryAxis.setLowerMargin(0.01);// 左边距 边框距离  
	    categoryAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
	    categoryAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 14));
	    // 横轴 lable 的位置 横轴上的 Lable 45度倾斜 DOWN_45
	    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
	    
	    plot.setRangeGridlinesVisible(true); //是否显示格子线
	    plot.setBackgroundAlpha(0.3f); //设置背景透明度
	    
	    //纵轴
	    NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
	    rangeAxis.setLabelFont(new Font("宋体", Font.BOLD, 12));
	    //设置工具工具提示字体样式
	    chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 12));
	    //如果是NumberAxis.createIntegerTickUnits()  代表y轴显示整数
	    rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());
	    rangeAxis.setAutoRangeIncludesZero(true);
	    rangeAxis.setUpperMargin(0.20);
	    rangeAxis.setLabelAngle(Math.PI / 2.0);
	    
	}
}
