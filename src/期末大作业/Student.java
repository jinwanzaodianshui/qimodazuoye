package 期末大作业;
import java.util.ArrayList;
import java.util.List;
public class Student {
	private String sno;  
	private String name;   
	private String className;  
	private String major; 
	private String password;  
	private List<Course> courseList=new ArrayList<Course>();  
	public Student() { }
	public Student(String sno,String name,String className,String major) {
		this.sno=sno;
		this.name=name;
		this.className=className;
		this.major=major;
		this.password=ConstantUtils.DEFAULT_PASSWORD;
	}
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Course> getCourseList() {
		return courseList;
	}

	public void setCourseList(List<Course> courseList) {
		this.courseList = courseList;
	}
	
	// 运动员1列出赛事通知
	public void printAllCourse() {
		if(GlobalData.courseList.isEmpty()) {
			System.out.println("暂无赛事记录！");		
		}else {
			System.out.println("=============赛事列表=============");
			String cInfo="";
			for(Course courseLoop:GlobalData.courseList) {
				String teacherName="";
				Teacher teacher=User.findTeacherByNo(courseLoop.getTeacherNo());
				if(teacher!=null) {
					teacherName=teacher.getTeacherName();
				}else {
					teacherName="待定";
				}
				cInfo=String.format("赛事编号：%s\t赛事名称：%s\t比赛时间：%s\t比赛地点：%s", courseLoop.getCourseNo(),courseLoop.getCourseName(),courseLoop.getClassTime(),courseLoop.getLocation());
				System.out.println(cInfo);
			}
		}
	}
	
	// 列出已参赛运动员
	public void printCourseList() {
		if(courseList.isEmpty()) {
			System.out.println("您还没有参加过任何比赛！");
		}else {
			System.out.printf("========已参加赛事列表(编号:%s  姓名:%s)========\n",this.sno,this.name);
			String courseInfo="";
			for(Course courseLoop:courseList) {
				String teacherName="";
				Teacher teacher=User.findTeacherByNo(courseLoop.getTeacherNo());
				if(teacher!=null) {
					teacherName=teacher.getTeacherName();
				}else {
					teacherName="待定";
				}
				courseInfo=String.format("赛事编号：%s\t赛事名称：%s\t比赛时间：%s\t比赛地点：%s", courseLoop.getCourseNo(),courseLoop.getCourseName(),courseLoop.getClassTime(),courseLoop.getLocation());
				System.out.println(courseInfo);
			}
		}
	}
	
	// 报名参赛运动员2
	public void addCousr(String courseNo) {
		Course course=User.findCourseByNo(courseNo);
		if(course!=null) {
			if(!isExistCourse(course.getCourseNo())) {
				//添加赛事
				this.courseList.add(course);
				
				//运动员报名的同时，在相应赛事中添加此参赛运动员
				course.addStudent(this);
				System.out.println("报名成功！");
			}else {
				System.out.println("你已报名参加此赛事，不能重复报名！");
			}
		}else {
			System.out.println("此赛事已停止报名！");
		}
	}
	public boolean isExistCourse(String courseNo) {
		boolean flag=false;
		for(Course courseLoop:courseList) {
			if(courseLoop.getCourseNo().equals(courseNo)) {
				flag=true;
				break;
			}
		}
		return flag;
	}
	
	//查询运动员信息
	public String toString() {
		String stuInfo=String.format("编号：%s\t姓名：%s\t性别：%s\t年龄：%s", this.getSno(),this.getName(),this.getClassName(),this.getMajor());
		return stuInfo;
	}
	
}
