package kg.itacademy.gsg.bootstrap;

import kg.itacademy.gsg.entities.*;
import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.enums.Status;
import kg.itacademy.gsg.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        User admin = User.builder()
                .email("admin@mail.ru")
                .firstName("Admin")
                .lastName("Admin")
                .password(passwordEncoder.encode("123"))
                .role(new UserRole("ROLE_ADMIN"))
                .build();

        User manager = User.builder()
                .email("manager@mail.ru")
                .firstName("Manager")
                .lastName("Manager")
                .password(passwordEncoder.encode("12"))
                .role(new UserRole("ROLE_MANAGER"))
                .build();

        User user = User.builder()
                .email("user@mail.ru")
                .firstName("User")
                .lastName("User")
                .password(passwordEncoder.encode("1"))
                .role(new UserRole("ROLE_USER"))
                .build();

        User user2 = User.builder()
                .email("user2@mail.ru")
                .firstName("User")
                .lastName("User")
                .password(passwordEncoder.encode("1"))
                .role(new UserRole("ROLE_USER"))
                .build();
        User user3 = User.builder()
                .email("user3@mail.ru")
                .firstName("User")
                .lastName("User")
                .password(passwordEncoder.encode("1"))
                .role(new UserRole("ROLE_USER"))
                .build();

        User user4 = User.builder()
                .email("user4@mail.ru")
                .firstName("User")
                .lastName("User")
                .password(passwordEncoder.encode("1"))
                .role(new UserRole("ROLE_USER"))
                .build();
        userRepository.save(admin);
//        userRepository.save(manager);
//        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);


//        List<Task> taskList = new ArrayList<>();
//        taskList.add(task);
//        taskList.add(task2);
//        taskList.add(task3);
//        taskList.add(task4);
        Package p = Package.builder()
                .title("Оптимальный")
                .description("Самый оптимальный вариант")
                .build();

        Package p2 = Package.builder()
                .title("Оптимальный")
                .description("Самый лучший вариант")
                .build();

        Package p3 = Package.builder()
                .title("Оптимальный")
                .description("Самый выгодный вариант")
                .build();

        Package p4 = Package.builder()
                .title("Оптимальный")
                .description("Самый экономный вариант")
                .build();

        Package p5 = Package.builder()
                .title("Оптимальный")
                .description("Самый экономный вариант")
                .build();

        Category category = Category.builder()
                .title("Введение социальных сетей")
                .packageId(p)
                .build();
        Category category1 = Category.builder()
                .title("Работа со СМИ")
                .packageId(p2)
                .build();
        Category category2 = Category.builder()
                .title("Создание видеороликов, инфографики и анимации")
                .packageId(p3)
                .build();
        Category category3 = Category.builder()
                .title("Создание медиа контент")
                .packageId(p4)
                .build();
//        categoryRepository.save(category);
        Task task = Task.builder()
                .title("Введение социальных сетей")
                .description("продвижение")
                .status(Status.TODO)
                .categoryId(category)
                .build();

        Task task2 = Task.builder()
                .title("Работа со СМИ")
                .description("интервью")
                .status(Status.TODO)
                .categoryId(category1)
                .build();

        Task task3 = Task.builder()
                .title("Создание видеороликов, инфографики и анимации")
                .description("что-то что-то")
                .status(Status.TODO)
                .categoryId(category2)
                .build();
        Task task4 = Task.builder()
                .title("Создание медиа контент")
                .description("фото съемка")
                .status(Status.TODO)
                .categoryId(category3)
                .build();
        taskRepository.save(task);
        taskRepository.save(task2);
        taskRepository.save(task3);
        taskRepository.save(task4);
//        Category category2 = Category.builder()
//                .title("SOmething")
//                .tasks(taskList)
//                .build();

        Order order = Order.builder()
                .title("Ресторан асман")
                .clientId(user)
                .managerId(manager)
                .packageId(p5)
                .build();
        orderRepository.save(order);
    }
}