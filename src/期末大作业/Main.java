package ��ĩ����ҵ;
import java.util.Scanner;

public class Main {
	
	static User currentAdmin=new User("admin","tom123@163.com");
	static Student currentStudent;
	static Teacher currentTeacher;
	static int currentUserCategory;
	
	public static void main(String[] args) {
		
		// ��ʼ������		
		initData();
		//��ʼ��¼
		String userId="",password="";
		boolean loginResult=false;
		
		Scanner input=new Scanner(System.in);
		while(true) {
			System.out.println("---------------�û���¼---------------");
			System.out.print("��ѡ���û�����[1-����Ա,2-�˶�Ա]��");
			String category=input.next();
			if(!category.equals("1")&&!category.equals("2")) {
				System.out.println("�û�����ѡ��������������룡");
				continue;
			}
			System.out.print("�������ʺţ�");
			userId=input.next();
			System.out.print("���������룺");
			password=input.next();
			currentUserCategory=Integer.valueOf(category);
			loginResult=login(currentUserCategory,userId,password);
			if(loginResult) {
				System.out.println("��¼�ɹ���");
				break;
			}else {
				System.out.println("�û����͡��ʺŻ�����������µ�¼(Y or N)��");
				String yn=input.next();
				if(StringUtils.isEmpty(yn) || !yn.toUpperCase().equals("Y")) {
					System.out.println("�˳���¼��");
					break;
				}
			}
		}
		//��¼�ɹ������벻ͬ�����û���������
		if(loginResult) {
			if(currentUserCategory==1) {
				adminInterface();
			}else if(currentUserCategory==2) {
				studentInterface();
			}
		}
		
		input.close();
	}
	/*��ʼ���������*/
	private static  void initData() {
		System.out.println("��ʼ��ʼ������......");
		//��ʼ���û�
		GlobalData.userList.add(new User("admin","123456@qq.com"));
		
		// ��ʼ���˶�Ա����
		currentAdmin.addStudent(new Student("2020100","�˶�ԱA","��","20"));
		currentAdmin.addStudent(new Student("2020101","�˶�ԱB","��","20"));
		currentAdmin.addStudent(new Student("2020102","�˶�ԱC","Ů","20"));
		currentAdmin.addStudent(new Student("2020103","�˶�ԱD","Ů","20"));

		
		//��ʼ��������
		currentAdmin.addCourse(new Course("100","20-60","ȫ��������1",40.0,200,"��һ14��00","������A"));
		currentAdmin.addCourse(new Course("101","20-60","ȫ��������2",40.0,200,"�ܶ�14��00","������B"));
		currentAdmin.addCourse(new Course("102","20-60","ȫ��������3",40.0,200,"����14��00","������C"));
		currentAdmin.addCourse(new Course("103","20-60","ȫ��������4",40.0,200,"����14��00","������D"));
		currentAdmin.addCourse(new Course("104","20-60","ȫ��������5",40.0,200,"����14��00","������E"));
		System.out.println("��ʼ��������ɣ�");
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

	//����Ա��������
	private static void adminInterface() {
		Scanner input=new Scanner(System.in);
		while(true) {
			System.out.println("-------------------��ӭʹ������������Ϣ����ϵͳ-------------------");
			System.out.printf("\t\t����Ա��[%s]\n",currentAdmin.getUsername());
			System.out.println("***�˶�Ա����***");
			System.out.println("\t[1] ��ѯĳ�˶�Ա��Ϣ");
			System.out.println("\t[2] ����˶�Ա��Ϣ");
			System.out.println("\t[3] �����˶�Ա�˻�");
		
			System.out.println("***���²���***");
			System.out.println("\t[4] �г�����������Ϣ");
			System.out.println("\t[5] ��ѯĳ������Ϣ");
			System.out.println("\t[6] ��������֪ͨ");
			System.out.println("\t[7] ֹͣ����");
			System.out.println("\t[0] �˳�����");
			System.out.println("-----------------------------------------------------");						
			System.out.print("���������ϲ˵���ţ�");
			int menuNumber=-1;
			String menuNumberStr=input.next();
			if(!StringUtils.isNumeric(menuNumberStr)) {
				System.out.println("ѡ���������������룡");
				pause();
				continue;
			}else {
				menuNumber=Integer.valueOf(menuNumberStr);
			}
			switch(menuNumber) {
				//�˶�Ա����
				case 1:
					System.out.print("�������˶�Ա��ţ�");
					String sno=input.next();
					Student student=currentAdmin.findStudentByNo(sno);
					if(student!=null) {
						System.out.println(student.toString());
					}else {
						System.out.println("δ�ҵ���");
					}
					break;
				case 2:
					Student newStu=new Student();
					System.out.print("�������ţ�");
					newStu.setSno(input.next());
					System.out.print("������������");
					newStu.setName(input.next());
					System.out.print("�������Ա�");
					input.nextLine();
					newStu.setClassName(input.nextLine());				
					System.out.print("���������䣺");
					newStu.setMajor(input.nextLine());
					if(StringUtils.isEmpty(newStu.getSno())||StringUtils.isEmpty(newStu.getName())) {
						System.out.println("��š��������");
					}else {
						currentAdmin.addStudent(newStu);
					}
					break;
				case 3:
					System.out.print("������Ҫ�����˶�Ա�ı�ţ�");
					sno=input.next();
					currentAdmin.removeStudent(sno);					
					break;		
				//���̲���
				case 4:
					currentAdmin.printCourseList();
					break;
				case 5:
					System.out.print("���������̱�ţ�");
					String courseNo=input.next();
					Course course=currentAdmin.findCourseByNo(courseNo);
					if(course!=null) {
						System.out.println(course.toString());
					}else {
						System.out.println("δ�ҵ�");
					}
					break;
				case 6:
					Course newCourse=new Course();
					System.out.print("���������±�ţ�");
					newCourse.setCourseNo(input.next());
					System.out.print("����������������ƣ�");
					newCourse.setTeacherNo(input.next());
					System.out.print("�����������ƣ�");
					newCourse.setCourseName(input.next());
					System.out.print("������·�̳��ȣ�");
					String credit=input.next();
					System.out.print("������μ��������ޣ�");
					String classHour=input.next();
					System.out.print("���������ʱ�䣺");
					input.nextLine();
					newCourse.setClassTime(input.nextLine());
					System.out.print("����������ص㣺");
					newCourse.setLocation(input.nextLine());
					newCourse.setCredit(Double.valueOf(credit));
					newCourse.setClassHour(Integer.valueOf(classHour));
					currentAdmin.addCourse(newCourse);
					break;
				case 7:
					System.out.print("������Ҫɾ�����µı�ţ�");
					courseNo=input.next();
					currentAdmin.removeCourse(courseNo);
					break;
				case 0:
					input.close();
					System.out.println("***ллʹ�ã��ټ�***");
					System.exit(0);
				default:
					System.out.println("ѡ���������������룡");
			}	
			if(menuNumber!=0) {
				pause();
			}
		}	
	}
	//�˶�Ա�û���������
	private static void studentInterface() {
		Scanner input=new Scanner(System.in);
		while(true) {
			System.out.println("-------------------��ӭʹ������������Ϣ����ϵͳ-------------------");	
			System.out.printf("\t\t�˶�Ա��[%s,%s]\n",currentStudent.getSno(),currentStudent.getName());
			System.out.println("\t[1] ��ѯ��������֪ͨ");
			System.out.println("\t[2] ��������");
			System.out.println("\t[3] �鿴�Ѳμ�����");
			System.out.println("\t[0] �˳�����");
			System.out.println("-----------------------------------------------------");			
			System.out.print("���������ϲ˵���ţ�");
			int menuNumber=-1;
			String menuNumberStr=input.next();
			if(!StringUtils.isNumeric(menuNumberStr)) {
				System.out.println("ѡ���������������룡");
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
				System.out.print("������Ҫ�μӵ����±�ţ�");
				String courseNo=input.next();
				currentStudent.addCousr(courseNo);
				break;
			case 3:
				currentStudent.printCourseList();
				break;
			case 0:
				input.close();
				System.out.println("***ллʹ�ã��ټ�***");
				System.exit(0);
			default:
				System.out.println("ѡ���������������룡");
			}	
			if(menuNumber!=0) {
				pause();
			}
		}		
	}

	public static void pause()   { //ͣ��
		System.out.print("���س�������......");
		new Scanner(System.in).nextLine();
	}
}


