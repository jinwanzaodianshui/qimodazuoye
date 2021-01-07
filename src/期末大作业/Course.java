package 期末大作业;
import java.util.ArrayList;
import java.util.List;

/** 
 * @author tlm
  *  课程类
 */
public class Course {
	private String courseNo;  
	private String teacherNo;  
	/* 赛程名称 */
	private String courseName;  
	/* 路程*/
	private Double credit;
	/* 人数上限*/
	private Integer  classHour;
	/* 比赛时间 */
	private String classTime;  
	/* 比赛地点 */
	private String location;  
	/* 参赛运动员列表 */
	private List<Student> studentList=new ArrayList<Student>();  
	
	public Course() {}
	
	public Course(String courseNo,String teacherNo,String courseName,Double credit,Integer  classHour,String classTime,String location) {
		this.courseNo=courseNo;
		this.teacherNo=teacherNo;
		this.courseName=courseName;
		this.credit=credit;
		this.classHour=classHour;
		this.classTime=classTime;
		this.location=location;
	}
	
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getTeacherNo() {
		return teacherNo;
	}
	public void setTeacherNo(String teacherNo) {
		this.teacherNo = teacherNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public Double getCredit() {
		return credit;
	}

	public void setCredit(Double credit) {
		this.credit = credit;
	}

	public Integer getClassHour() {
		return classHour;
	}

	public void setClassHour(Integer classHour) {
		this.classHour = classHour;
	}

	public String getClassTime() {
		return classTime;
	}
	public void setClassTime(String classTime) {
		this.classTime = classTime;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	
	//添加参赛运动员
	public void addStudent(Student student) {
		this.studentList.add(student);
	}
	
	//移除参赛运动员
	public void removeStudent(Student student) {
		this.studentList.remove(student);
	}
	
	public String toString() {
		String teacherName="";
		Teacher teacher=User.findTeacherByNo(this.getTeacherNo());
		if(teacher!=null) {
			teacherName=teacher.getTeacherName();
		}else {
			teacherName="待定";
		}
		String info=String.format("赛事编号：%s\t赛事名称：%s\t参赛时间：%s\t参赛地点：%s", this.getCourseNo(),this.getCourseName(),this.getClassTime(),this.getLocation());

		return info;
	}
	
}
