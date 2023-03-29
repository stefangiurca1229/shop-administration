//package com.myshopexample.security.config;
//
//import com.myshopexample.model.admin.Administrator;
//import com.myshopexample.model.admin.Role;
//import com.myshopexample.model.department.Department;
//import com.myshopexample.repositories.AdministratorRepository;
//import com.myshopexample.repositories.DepartmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//
//@Component
//public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
//    private boolean alreadySetup = false;
//    @Autowired
//    private DepartmentRepository departmentRepository;
//    @Autowired
//    private AdministratorRepository administratorRepository;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if(!alreadySetup){
//            Department departmentIt = new Department();
//            Department departmentFashion = new Department();
//            Administrator administratorIt = new Administrator("it","it", Role.ROLE_PRODUCT_ADMINISTRATOR);
//            Administrator administratorFashion = new Administrator("fashion","fashion",Role.ROLE_PRODUCT_ADMINISTRATOR);
//            Administrator itStockAdministrator = new Administrator("stockIt","stockIt",Role.ROLE_STOCK_ADMINISTRATOR);
//            Administrator fashionAdministrator = new Administrator("stockFashion","stockFashion",Role.ROLE_STOCK_ADMINISTRATOR);
//            departmentIt.setName("It");
//            departmentFashion.setName("Fashion");
//            departmentRepository.save(departmentIt);
//            departmentRepository.save(departmentFashion);
//            itStockAdministrator.setDepartments(new ArrayList<>(){{add(departmentIt);}});
//            administratorIt.setDepartments(new ArrayList<>(){{add(departmentIt);}});
//            administratorFashion.setDepartments(new ArrayList<>(){{add(departmentFashion);}});
//            fashionAdministrator.setDepartments(new ArrayList<>(){{add(departmentFashion);}});
//            administratorRepository.save(itStockAdministrator);
//            administratorRepository.save(administratorIt);
//            administratorRepository.save(fashionAdministrator);
//            administratorRepository.save(administratorFashion);
//        }
//        alreadySetup = true;
//    }
//}
