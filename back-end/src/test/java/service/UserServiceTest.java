package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private TeacherRepository teacherRepository;

    @Mock
    private ClassRepository classRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testLoginTeacherSuccess() {
        String id = "teacher1";
        String password = "password";
        Teacher mockTeacher = new Teacher(id, password, "张三", (byte) 40, "男", "数学");
        when(teacherRepository.findTeacherByIdAndPassword(id, password)).thenReturn(mockTeacher);

        User user = userService.login(id, password);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(password, user.getPassword());
        verify(teacherRepository).findTeacherByIdAndPassword(id, password);
    }

    @Test
    public void testLoginStudentSuccess() {
        String id = "student1";
        String password = "password";
        Class mockClass = classRepository.findByClassName("软工一班");
        Student mockStudent = new Student(id, password, "李四", (byte) 20, "女", "计算机科学", mockClass);
        when(studentRepository.findStudentByIdAndPassword(id, password)).thenReturn(mockStudent);

        User user = userService.login(id, password);

        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(password, user.getPassword());
        verify(studentRepository).findStudentByIdAndPassword(id, password);
    }

    @Test
    public void testRegisterStudent() {
        // 测试数据
        String id = "student2";
        String password = "password";
        String name = "王五";
        byte age = 22;
        String gender = "男";
        String department = "物理";
        String className = "软工一班";
        // 创建模拟的Class对象
        Class mockClass = classRepository.findByClassName("软工一班");
        // 当调用findByName时，返回mockClass
        when(classRepository.findByClassName(className)).thenReturn(mockClass);
        // 调用注册方法
        userService.register(id, password, name, age, gender, department, className);
        // 验证classRepository的findByName方法是否被调用了一次
        verify(classRepository,times(2)).findByClassName(className);
        // 验证studentRepository的addStudent方法是否被调用了一次，参数任意
        verify(studentRepository).save(any(Student.class));
    }


    @Test
    public void testRegisterTeacher() {
        String id = "teacher2";
        String password = "password";
        String name = "赵六";
        byte age = 35;
        String gender = "女";
        String department = "英语";

        userService.register(id, password, name, age, gender, department);

        verify(teacherRepository).save(any(Teacher.class));
    }

    @Test
    public void testFindStudentById() {
        String id = "student1";
        Class mockClass = classRepository.findByClassName("软工一班");
        Student mockStudent = new Student(id, "password", "李四", (byte) 20, "女", "计算机科学", mockClass);
        when(studentRepository.findById(id)).thenReturn(Optional.of(mockStudent));

        User user = userService.findStudentById(id);

        assertNotNull(user);
        assertEquals(id, user.getId());
        verify(studentRepository).findById(id);
    }

    @Test
    public void testFindTeacherById() {
        String id = "teacher1";
        Teacher mockTeacher = new Teacher(id, "password", "张三", (byte) 40, "男", "数学");
        when(teacherRepository.findById(id)).thenReturn(Optional.of(mockTeacher));

        User user = userService.findTeacherById(id);

        assertNotNull(user);
        assertEquals(id, user.getId());
        verify(teacherRepository).findById(id);
    }
}
