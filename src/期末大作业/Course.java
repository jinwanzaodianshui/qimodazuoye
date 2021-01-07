package ��ĩ����ҵ;
import java.util.ArrayList;
import java.util.List;

/** 
 * @author tlm
  *  �γ���
 */
public class Course {
	private String courseNo;  
	private String teacherNo;  
	/* �������� */
	private String courseName;  
	/* ·��*/
	private Double credit;
	/* ��������*/
	private Integer  classHour;
	/* ����ʱ�� */
	private String classTime;  
	/* �����ص� */
	private String location;  
	/* �����˶�Ա�б� */
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
	
	//��Ӳ����˶�Ա
	public void addStudent(Student student) {
		this.studentList.add(student);
	}
	
	//�Ƴ������˶�Ա
	public void removeStudent(Student student) {
		this.studentList.remove(student);
	}
	
	public String toString() {
		String teacherName="";
		Teacher teacher=User.findTeacherByNo(this.getTeacherNo());
		if(teacher!=null) {
			teacherName=teacher.getTeacherName();
		}else {
			teacherName="����";
		}
		String info=String.format("���±�ţ�%s\t�������ƣ�%s\t����ʱ�䣺%s\t�����ص㣺%s", this.getCourseNo(),this.getCourseName(),this.getClassTime(),this.getLocation());

		return info;
	}
	
}
