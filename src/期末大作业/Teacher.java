package 期末大作业;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
	private String teacherNo; 
	private String teacherName;
	private String deptName;
	private String mobilePhone; 
	private String password;
	private List<Course>  courseList=new ArrayList<Course>();
	
	public Teacher() { }
	
	public Teacher(String teacherNo,String teacherName,String deptName,String mobilePhone) {
		this.teacherNo=teacherNo;
		this.teacherName=teacherName;
		this.deptName=deptName;
		this.mobilePhone=mobilePhone;
		this.password=ConstantUtils.DEFAULT_PASSWORD;
	}
	
	public String getTeacherNo() {
		return teacherNo;
	}
	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
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
	
	public void addCourse(Course course) {
		this.courseList.add(course);
	}
	public void removeCourse(Course course) {
		this.courseList.remove(course);
	}

}