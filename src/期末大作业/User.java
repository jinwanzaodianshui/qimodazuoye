package 期末大作业;
public class User {
	private String username;  
	/* 用户密码 */
	private String password;
	/* 邮箱 */
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
	//2新增运动员
	public void addStudent(Student student) {
		Student stu=findStudentByNo(student.getSno());
		if(stu==null) {
			GlobalData.studentList.add(student);
			System.out.println("运动员记录成功添加！");
		}else {
			System.out.println("此运动员记录已存在！");
		}
	}
	//3禁用运动员
	public void removeStudent(String sno) {
		Student student=findStudentByNo(sno);
		if(student!=null) {
			GlobalData.studentList.remove(student);
			System.out.println("禁用成功！");
		}else {
			System.out.println("要禁用的运动员记录不存在！");
		}
	}

	/*1根据编号查找运动员记录*/
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

	//新增课程
	public void addCourse(Course course) {
		Course c=findCourseByNo(course.getCourseNo());
		if(c==null) {
			//新增课程
			GlobalData.courseList.add(course);
			Teacher teacher=findTeacherByNo(course.getTeacherNo());
			if(teacher!=null) teacher.addCourse(course);
			System.out.println("赛事通知成功发布！");
		}else {
			System.out.println("此赛事通知已存在！");
		}
		
	}
	// 删除课程
	public void removeCourse(String courseNo) {
		Course c=findCourseByNo(courseNo);
		if(c!=null) {
			//删除课程
			GlobalData.courseList.remove(c);
			Teacher teacher=findTeacherByNo(c.getTeacherNo());
			teacher.removeCourse(c);
			System.out.println("删除成功！");
		}else {
			System.out.println("要删除的课程记录不存在！");
		}	
	}
	// 打印全部赛事信息
	public static void printCourseList( ) {
		if(GlobalData.courseList.isEmpty()) {
			System.out.println("暂无赛事记录！");		
		}else {
			String cInfo="";
			System.out.println("=============赛事列表=============");
			for(Course courseLoop:GlobalData.courseList) {
				String teacherName="";
				Teacher teacher=findTeacherByNo(courseLoop.getTeacherNo());
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
	
	/*根据课程编号查找课程记录*/
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
	
	/*根据用户名查找用户*/
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