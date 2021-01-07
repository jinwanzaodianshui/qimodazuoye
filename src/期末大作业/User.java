package ��ĩ����ҵ;
public class User {
	private String username;  
	/* �û����� */
	private String password;
	/* ���� */
	private String email;   
	public User() {}
	public User(String username,String email) {
		this.username=username;
		this.email=email;
		this.password=ConstantUtils.DEFAULT_PASSWORD;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	//2�����˶�Ա
	public void addStudent(Student student) {
		Student stu=findStudentByNo(student.getSno());
		if(stu==null) {
			GlobalData.studentList.add(student);
			System.out.println("�˶�Ա��¼�ɹ���ӣ�");
		}else {
			System.out.println("���˶�Ա��¼�Ѵ��ڣ�");
		}
	}
	//3�����˶�Ա
	public void removeStudent(String sno) {
		Student student=findStudentByNo(sno);
		if(student!=null) {
			GlobalData.studentList.remove(student);
			System.out.println("���óɹ���");
		}else {
			System.out.println("Ҫ���õ��˶�Ա��¼�����ڣ�");
		}
	}

	/*1���ݱ�Ų����˶�Ա��¼*/
	public static Student findStudentByNo(String studentNo) {
		Student student=null;
		if(!GlobalData.studentList.isEmpty()) {
			for(Student studentLoop:GlobalData.studentList) {
				if(studentLoop.getSno().equals(studentNo)) {
					student=studentLoop;
					break;
				}
			}
		}
		return student;
	}
	public static Teacher findTeacherByNo(String teacherNo) {
		Teacher teacher=null;
		if(!GlobalData.teacherList.isEmpty()) {
			for(Teacher teacherLoop:GlobalData.teacherList) {
				if(teacherLoop.getTeacherNo().equals(teacherNo)) {
					teacher=teacherLoop;
					break;
				}
			}
		}
		return teacher;
	}

	//�����γ�
	public void addCourse(Course course) {
		Course c=findCourseByNo(course.getCourseNo());
		if(c==null) {
			//�����γ�
			GlobalData.courseList.add(course);
			Teacher teacher=findTeacherByNo(course.getTeacherNo());
			if(teacher!=null) teacher.addCourse(course);
			System.out.println("����֪ͨ�ɹ�������");
		}else {
			System.out.println("������֪ͨ�Ѵ��ڣ�");
		}
		
	}
	// ɾ���γ�
	public void removeCourse(String courseNo) {
		Course c=findCourseByNo(courseNo);
		if(c!=null) {
			//ɾ���γ�
			GlobalData.courseList.remove(c);
			Teacher teacher=findTeacherByNo(c.getTeacherNo());
			teacher.removeCourse(c);
			System.out.println("ɾ���ɹ���");
		}else {
			System.out.println("Ҫɾ���Ŀγ̼�¼�����ڣ�");
		}	
	}
	// ��ӡȫ��������Ϣ
	public static void printCourseList( ) {
		if(GlobalData.courseList.isEmpty()) {
			System.out.println("�������¼�¼��");		
		}else {
			String cInfo="";
			System.out.println("=============�����б�=============");
			for(Course courseLoop:GlobalData.courseList) {
				String teacherName="";
				Teacher teacher=findTeacherByNo(courseLoop.getTeacherNo());
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
	
	/*���ݿγ̱�Ų��ҿγ̼�¼*/
	public static Course findCourseByNo(String courseNo) {
		Course course=null;
		if(!GlobalData.courseList.isEmpty()) {
			for(Course courseLoop:GlobalData.courseList) {
				if(courseLoop.getCourseNo().equals(courseNo)) {
					course=courseLoop;
					break;
				}
			}
		}
		return course;
	}
	
	/*�����û��������û�*/
	public static User findUserByUsername(String username) {
		User user=null;
		if(!GlobalData.userList.isEmpty()) {
			for(User userLoop:GlobalData.userList) {
				if(userLoop.getUsername().equals(username)) {
					user=userLoop;
					break;
				}
			}
		}
		return user;
	}
}