package kg.itacademy.gsg.bootstrap;

import kg.itacademy.gsg.entities.*;
import kg.itacademy.gsg.entities.Package;
import kg.itacademy.gsg.enums.Status;
import kg.itacademy.gsg.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

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

        UserRole roleUser = UserRole.builder()
                .roleName("ROLE_USER")
                .build();
        UserRole roleManager = UserRole.builder()
                .roleName("ROLE_MANAGER")
                .build();
        UserRole roleAdmin = UserRole.builder()
                .roleName("ROLE_ADMIN")
                .build();

        User admin = User.builder()
                .email("admin@mail.ru")
                .firstName("Bekbolot")
                .lastName("Nurmanbetov")
                .password(passwordEncoder.encode("123"))
                .role(roleAdmin)
                .build();

        User manager = User.builder()
                .email("manager@mail.ru")
                .firstName("Bakai")
                .lastName("Kydyrbek uulu")
                .password(passwordEncoder.encode("12"))
                .role(roleManager)
                .build();

        User user = User.builder()
                .email("zhalilov.atai@mail.ru")
                .firstName("Атай")
                .lastName("Жалилов")
                .password(passwordEncoder.encode("1"))
                .role(roleUser)
                .build();

        User user2 = User.builder()
                .email("kanatova.sezim@mail.ru")
                .firstName("Сезим")
                .lastName("Канатова")
                .password(passwordEncoder.encode("1"))
                .role(roleUser)
                .build();
        User user3 = User.builder()
                .email("surakmatov.aziz@mail.ru")
                .firstName("Азиз")
                .lastName("Суракматов")
                .password(passwordEncoder.encode("1"))
                .role(roleUser)
                .build();

        userRoleRepository.save(roleAdmin);
        userRoleRepository.save(roleManager);
        userRoleRepository.save(roleUser);

        userRepository.save(admin);
        userRepository.save(manager);
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);

        Package p = Package.builder()
                .title("Экономный")
                .description("Вариант с малым бюджетом")
                .build();

        Package p2 = Package.builder()
                .title("Оптимальный")
                .description("Оптимальный вариант самый часто используемый пакет")
                .build();

        Package p3 = Package.builder()
                .title("Профессиональный")
                .description("Пакет ПРО 5 extra услуг")
                .build();


        Category category1 = Category.builder()
                .title("Введение социальных сетей")
                .packageId(p)
                .build();
        Category category2 = Category.builder()
                .title("Работа со СМИ")
                .packageId(p2)
                .build();
        Category category3 = Category.builder()
                .title("Создание видеороликов, инфографики и анимации")
                .packageId(p3)
                .build();


        Task task = Task.builder()
                .title("Продвижение")
                .description("ведение аккаунта в инстаграм")
                .categoryId(category1)
                .build();

        Task task2 = Task.builder()
                .title("Дать рекламу в газете")
                .description("Составить договор с газетами на рекламу")
                .categoryId(category2)
                .build();

        Task task3 = Task.builder()
                .title("Снять-2 минутное видео")
                .description("Выпускное видео для абитуриентов")
                .categoryId(category3)
                .build();
        packageRepository.save(p);
        packageRepository.save(p2);
        packageRepository.save(p3);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        taskRepository.save(task);
        taskRepository.save(task2);
        taskRepository.save(task3);
    }
}