package 期末大作业;
import java.util.Scanner;

public class Main {
	
	static User currentAdmin=new User("admin","tom123@163.com");
	static Student currentStudent;
	static Teacher currentTeacher;
	static int currentUserCategory;
	
	public static void main(String[] args) {
		
		// 初始化数据		
		initData();
		//开始登录
		String userId="",password="";
		boolean loginResult=false;
		
		Scanner input=new Scanner(System.in);
		while(true) {
			System.out.println("---------------用户登录---------------");
			System.out.print("请选择用户类型[1-管理员,2-运动员]：");
			String category=input.next();
			if(!category.equals("1")&&!category.equals("2")) {
				System.out.println("用户类型选择错误，请重新输入！");
				continue;
			}
			System.out.print("请输入帐号：");
			userId=input.next();
			System.out.print("请输入密码：");
			password=input.next();
			currentUserCategory=Integer.valueOf(category);
			loginResult=login(currentUserCategory,userId,password);
			if(loginResult) {
				System.out.println("登录成功！");
				break;
			}else {
				System.out.println("用户类型、帐号或密码错误！重新登录(Y or N)：");
				String yn=input.next();
				if(StringUtils.isEmpty(yn) || !yn.toUpperCase().equals("Y")) {
					System.out.println("退出登录！");
					break;
				}
			}
		}
		//登录成功，进入不同类型用户操作界面
		if(loginResult) {
			if(currentUserCategory==1) {
				adminInterface();
			}else if(currentUserCategory==2) {
				studentInterface();
			}
		}
		
		input.close();
	}
	/*初始化相关数据*/
	private static  void initData() {
		System.out.println("开始初始化数据......");
		//初始化用户
		GlobalData.userList.add(new User("admin","123456@qq.com"));
		
		// 初始化运动员数据
		currentAdmin.addStudent(new Student("2020100","运动员A","男","20"));
		currentAdmin.addStudent(new Student("2020101","运动员B","男","20"));
		currentAdmin.addStudent(new Student("2020102","运动员C","女","20"));
		currentAdmin.addStudent(new Student("2020103","运动员D","女","20"));

		
		//初始化赛数据
		currentAdmin.addCourse(new Course("100","20-60","全程马拉松1",40.0,200,"周一14：00","体育场A"));
		currentAdmin.addCourse(new Course("101","20-60","全程马拉松2",40.0,200,"周二14：00","体育场B"));
		currentAdmin.addCourse(new Course("102","20-60","全程马拉松3",40.0,200,"周三14：00","体育场C"));
		currentAdmin.addCourse(new Course("103","20-60","全程马拉松4",40.0,200,"周四14：00","体育场D"));
		currentAdmin.addCourse(new Course("104","20-60","全程马拉松5",40.0,200,"周五14：00","体育场E"));
		System.out.println("初始化数据完成！");
	}

	private static boolean login(int userCategory,String userId,String password) {
		boolean result=false;
		if(userCategory==1) {
			User user=User.findUserByUsername(userId);
			if(user!=null && user.getPassword().equals(password)) {
				result=true;
				currentAdmin=user;
			}
		}else if(userCategory==2) {
			Student student=User.findStudentByNo(userId);
			if(student!=null && student.getPassword().equals(password)) {
				result=true;
				currentStudent=student;
			}
		}
		return result;
	}

	//管理员操作界面
	private static void adminInterface() {
		Scanner input=new Scanner(System.in);
		while(true) {
			System.out.println("-------------------欢迎使用马拉松赛信息管理系统-------------------");
			System.out.printf("\t\t管理员端[%s]\n",currentAdmin.getUsername());
			System.out.println("***运动员操作***");
			System.out.println("\t[1] 查询某运动员信息");
			System.out.println("\t[2] 添加运动员信息");
			System.out.println("\t[3] 禁用运动员账户");
		
			System.out.println("***赛事操作***");
			System.out.println("\t[4] 列出所有赛事信息");
			System.out.println("\t[5] 查询某赛事信息");
			System.out.println("\t[6] 发布赛事通知");
			System.out.println("\t[7] 停止赛事");
			System.out.println("\t[0] 退出操作");
			System.out.println("-----------------------------------------------------");						
			System.out.print("请输入以上菜单序号：");
			int menuNumber=-1;
			String menuNumberStr=input.next();
			if(!StringUtils.isNumeric(menuNumberStr)) {
				System.out.println("选择有误，请重新输入！");
				pause();
				continue;
			}else {
				menuNumber=Integer.valueOf(menuNumberStr);
			}
			switch(menuNumber) {
				//运动员操作
				case 1:
					System.out.print("请输入运动员编号：");
					String sno=input.next();
					Student student=currentAdmin.findStudentByNo(sno);
					if(student!=null) {
						System.out.println(student.toString());
					}else {
						System.out.println("未找到！");
					}
					break;
				case 2:
					Student newStu=new Student();
					System.out.print("请输入编号：");
					newStu.setSno(input.next());
					System.out.print("请输入姓名：");
					newStu.setName(input.next());
					System.out.print("请输入性别：");
					input.nextLine();
					newStu.setClassName(input.nextLine());				
					System.out.print("请输入年龄：");
					newStu.setMajor(input.nextLine());
					if(StringUtils.isEmpty(newStu.getSno())||StringUtils.isEmpty(newStu.getName())) {
						System.out.println("编号、姓名必填！");
					}else {
						currentAdmin.addStudent(newStu);
					}
					break;
				case 3:
					System.out.print("请输入要禁用运动员的编号：");
					sno=input.next();
					currentAdmin.removeStudent(sno);					
					break;		
				//赛程操作
				case 4:
					currentAdmin.printCourseList();
					break;
				case 5:
					System.out.print("请输入赛程编号：");
					String courseNo=input.next();
					Course course=currentAdmin.findCourseByNo(courseNo);
					if(course!=null) {
						System.out.println(course.toString());
					}else {
						System.out.println("未找到");
					}
					break;
				case 6:
					Course newCourse=new Course();
					System.out.print("请输入赛事编号：");
					newCourse.setCourseNo(input.next());
					System.out.print("请输入参赛年龄限制：");
					newCourse.setTeacherNo(input.next());
					System.out.print("请输赛事名称：");
					newCourse.setCourseName(input.next());
					System.out.print("请输入路程长度：");
					String credit=input.next();
					System.out.print("请输入参加人数上限：");
					String classHour=input.next();
					System.out.print("请输入比赛时间：");
					input.nextLine();
					newCourse.setClassTime(input.nextLine());
					System.out.print("请输入比赛地点：");
					newCourse.setLocation(input.nextLine());
					newCourse.setCredit(Double.valueOf(credit));
					newCourse.setClassHour(Integer.valueOf(classHour));
					currentAdmin.addCourse(newCourse);
					break;
				case 7:
					System.out.print("请输入要删除赛事的编号：");
					courseNo=input.next();
					currentAdmin.removeCourse(courseNo);
					break;
				case 0:
					input.close();
					System.out.println("***谢谢使用，再见***");
					System.exit(0);
				default:
					System.out.println("选择有误，请重新输入！");
			}	
			if(menuNumber!=0) {
				pause();
			}
		}	
	}
	//运动员用户操作界面
	private static void studentInterface() {
		Scanner input=new Scanner(System.in);
		while(true) {
			System.out.println("-------------------欢迎使用马拉松赛信息管理系统-------------------");	
			System.out.printf("\t\t运动员端[%s,%s]\n",currentStudent.getSno(),currentStudent.getName());
			System.out.println("\t[1] 查询所有赛事通知");
			System.out.println("\t[2] 报名参赛");
			System.out.println("\t[3] 查看已参加赛事");
			System.out.println("\t[0] 退出操作");
			System.out.println("-----------------------------------------------------");			
			System.out.print("请输入以上菜单序号：");
			int menuNumber=-1;
			String menuNumberStr=input.next();
			if(!StringUtils.isNumeric(menuNumberStr)) {
				System.out.println("选择有误，请重新输入！");
				pause();
				continue;
			}else {
				menuNumber=Integer.valueOf(menuNumberStr);
			}
			switch(menuNumber) {
			case 1:
				currentStudent.printAllCourse();
				break;
			case 2:
				System.out.print("请输入要参加的赛事编号：");
				String courseNo=input.next();
				currentStudent.addCousr(courseNo);
				break;
			case 3:
				currentStudent.printCourseList();
				break;
			case 0:
				input.close();
				System.out.println("***谢谢使用，再见***");
				System.exit(0);
			default:
				System.out.println("选择有误，请重新输入！");
			}	
			if(menuNumber!=0) {
				pause();
			}
		}		
	}

	public static void pause()   { //停顿
		System.out.print("按回车键继续......");
		new Scanner(System.in).nextLine();
	}
}


