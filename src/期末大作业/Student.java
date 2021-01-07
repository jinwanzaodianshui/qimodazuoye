package ��ĩ����ҵ;
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
	
	// �˶�Ա1�г�����֪ͨ
	public void printAllCourse() {
		if(GlobalData.courseList.isEmpty()) {
			System.out.println("�������¼�¼��");		
		}else {
			System.out.println("=============�����б�=============");
			String cInfo="";
			for(Course courseLoop:GlobalData.courseList) {
				String teacherName="";
				Teacher teacher=User.findTeacherByNo(courseLoop.getTeacherNo());
				if(teacher!=null) {
					teacherName=teacher.getTeacherName();
				}else {
					teacherName="����";
				}
				cInfo=String.format("���±�ţ�%s\t�������ƣ�%s\t����ʱ�䣺%s\t�����ص㣺%s", courseLoop.getCourseNo(),courseLoop.getCourseName(),courseLoop.getClassTime(),courseLoop.getLocation());
				System.out.println(cInfo);
			}
		}
	}
	
	// �г��Ѳ����˶�Ա
	public void printCourseList() {
		if(courseList.isEmpty()) {
			System.out.println("����û�вμӹ��κα�����");
		}else {
			System.out.printf("========�Ѳμ������б�(���:%s  ����:%s)========\n",this.sno,this.name);
			String courseInfo="";
			for(Course courseLoop:courseList) {
				String teacherName="";
				Teacher teacher=User.findTeacherByNo(courseLoop.getTeacherNo());
				if(teacher!=null) {
					teacherName=teacher.getTeacherName();
				}else {
					teacherName="����";
				}
				courseInfo=String.format("���±�ţ�%s\t�������ƣ�%s\t����ʱ�䣺%s\t�����ص㣺%s", courseLoop.getCourseNo(),courseLoop.getCourseName(),courseLoop.getClassTime(),courseLoop.getLocation());
				System.out.println(courseInfo);
			}
		}
	}
	
	// ���������˶�Ա2
	public void addCousr(String courseNo) {
		Course course=User.findCourseByNo(courseNo);
		if(course!=null) {
			if(!isExistCourse(course.getCourseNo())) {
				//�������
				this.courseList.add(course);
				
				//�˶�Ա������ͬʱ������Ӧ��������Ӵ˲����˶�Ա
				course.addStudent(this);
				System.out.println("�����ɹ���");
			}else {
				System.out.println("���ѱ����μӴ����£������ظ�������");
			}
		}else {
			System.out.println("��������ֹͣ������");
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
	
	//��ѯ�˶�Ա��Ϣ
	public String toString() {
		String stuInfo=String.format("��ţ�%s\t������%s\t�Ա�%s\t���䣺%s", this.getSno(),this.getName(),this.getClassName(),this.getMajor());
		return stuInfo;
	}
	
}
